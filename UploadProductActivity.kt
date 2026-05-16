package com.hallisanthe.ui.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hallisanthe.R
import com.hallisanthe.databinding.ActivityUploadProductBinding
import com.hallisanthe.model.Category
import com.hallisanthe.model.Product
import com.hallisanthe.repository.ProductRepository
import com.hallisanthe.repository.Result
import kotlinx.coroutines.launch
import java.util.Locale

class UploadProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadProductBinding
    private var selectedImageUri: Uri? = null
    private val repo by lazy { ProductRepository(this) }
    private var capturedLat: Double = 0.0
    private var capturedLng: Double = 0.0

    private val pickImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            selectedImageUri?.let {
                Glide.with(this).load(it).centerCrop().into(binding.ivPreview)
                binding.tvPickImage.visibility = View.GONE
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.upload_product)

        setupCategoryDropdown()
        setupLanguageToggle()

        binding.cardImage.setOnClickListener { openGallery() }
        binding.btnGetLocation.setOnClickListener { captureLocation() }
        binding.btnUpload.setOnClickListener { validateAndUpload() }
    }

    private fun captureLocation() {
        // Simulation: In a real app, this would use FusedLocationProvider or a Map Picker Activity
        capturedLat = 12.9716 // Example: Bangalore
        capturedLng = 77.5946
        binding.btnGetLocation.text = "📍 Location Captured: $capturedLat, $capturedLng"
        binding.btnGetLocation.setTextColor(resources.getColor(R.color.market_green, null))
        Toast.makeText(this, "Location details captured successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun setupLanguageToggle() {
        binding.btnToggleLanguages.setOnClickListener {
            if (binding.layoutOtherLanguages.visibility == View.GONE) {
                binding.layoutOtherLanguages.visibility = View.VISIBLE
                binding.btnToggleLanguages.text = "- Hide other languages"
            } else {
                binding.layoutOtherLanguages.visibility = View.GONE
                binding.btnToggleLanguages.text = "+ Add name in other languages (Optional)"
            }
        }
    }

    private fun setupCategoryDropdown() {
        val categories = Category.values()
            .filter { it != Category.ALL }
            .map { "${it.emoji} ${it.displayName}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.actvCategory.setAdapter(adapter)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImage.launch(intent)
    }

    private fun validateAndUpload() {
        val name     = binding.etProductName.text.toString().trim()
        var nameKn   = binding.etProductNameKn.text.toString().trim()
        var nameHi   = binding.etProductNameHi.text.toString().trim()
        var nameTa   = binding.etProductNameTa.text.toString().trim()
        val priceStr = binding.etPrice.text.toString().trim()
        val desc     = binding.etDescription.text.toString().trim()
        val catStr   = binding.actvCategory.text.toString().trim()
        val seller   = binding.etSellerName.text.toString().trim()
        val phone    = binding.etSellerPhone.text.toString().trim()
        val location = binding.etSellerLocation.text.toString().trim()
        val stockStr = binding.etStock.text.toString().trim()

        if (name.isEmpty()) { binding.tilProductName.error = "Required"; return }
        if (priceStr.isEmpty()) { binding.tilPrice.error = "Required"; return }
        if (catStr.isEmpty()) { binding.tilCategory.error = "Select category"; return }
        if (seller.isEmpty()) { binding.tilSellerName.error = "Required"; return }

        val price = priceStr.toDoubleOrNull() ?: run {
            binding.tilPrice.error = "Invalid price"; return
        }
        val stock = stockStr.toIntOrNull() ?: 0

        val currentLang = resources.configuration.locale.language
        if (nameKn.isEmpty() && currentLang == "kn") nameKn = name
        if (nameHi.isEmpty() && currentLang == "hi") nameHi = name
        if (nameTa.isEmpty() && currentLang == "ta") nameTa = name

        val cat = Category.values().firstOrNull { catStr.contains(it.displayName) }?.name ?: "POTTERY"

        val product = Product(
            name = name, nameKn = nameKn, nameHi = nameHi, nameTa = nameTa,
            price = price, description = desc, category = cat,
            sellerName = seller, sellerPhone = phone, sellerLocation = location,
            latitude = capturedLat, longitude = capturedLng,
            stock = stock
        )

        binding.btnUpload.isEnabled = false
        binding.progressUpload.visibility = View.VISIBLE

        lifecycleScope.launch {
            val result = if (selectedImageUri != null) {
                repo.uploadProduct(product, selectedImageUri!!)
            } else {
                repo.uploadProductNoImage(product)
            }

            binding.btnUpload.isEnabled = true
            binding.progressUpload.visibility = View.GONE

            when (result) {
                is Result.Success -> {
                    Toast.makeText(this@UploadProductActivity, "✅ Product listed successfully!", Toast.LENGTH_LONG).show()
                    finish()
                }
                is Result.Error -> {
                    Toast.makeText(this@UploadProductActivity, "❌ ${result.message}", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) { onBackPressedDispatcher.onBackPressed(); return true }
        return super.onOptionsItemSelected(item)
    }
}
