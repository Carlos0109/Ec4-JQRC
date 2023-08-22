
package com.carlos.ec03_qrjc.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.carlos.ec03_qrjc.databinding.ActivityAddDigimonBinding
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddDigimonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDigimonBinding
    private lateinit var openGalleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var firestore: FirebaseFirestore

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDigimonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = Firebase.firestore

        binding.btnTomarFoto.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            openGalleryLauncher.launch(galleryIntent)
        }

        openGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val selectedImageUri = result.data?.data
                selectedImageUri?.let {
                    Glide.with(this).load(it).into(binding.ivphoto)
                    uploadImageToFirebase(selectedImageUri)
                }
            }
        }/*
        binding.btnRegisterCoupon.setOnClickListener{
            val name:String = binding.tilCouponName.editText?.text.toString()
            val level:String = binding.tilCouponLevel.editText?.text.toString()
            if (name.isNotEmpty()&& level.isNotEmpty()){
                val coupon = hashMapOf(
                    "name" to name,
                    "level" to level,
                    "img" to ""
                )
                firestore.collection("digimon")
                    .add(coupon)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Agregado correctamente con id:${it.id}", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Agrego el elemento", Toast.LENGTH_SHORT).show()
                    }
            }
        }*/
    }
    private fun uploadImageToFirebase(imageUri: Uri?) {
        if (imageUri != null) {
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("images/${UUID.randomUUID()}")
            val uploadTask = imageRef.putFile(imageUri)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    binding.ivphoto.setImageURI(imageUri) // Mostrar la imagen en ImageView
                    saveCouponToFirestore(downloadUri.toString()) // Guardar la URL en Firestore
                } else {
                    // Manejar el error de obtener la URL
                    Toast.makeText(this, "Error al obtener la URL de la imagen", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun saveCouponToFirestore(imageUrl: String) {
        val name:String = binding.tilCouponName.editText?.text.toString()
        val level:String = binding.tilCouponLevel.editText?.text.toString()

        if (name.isNotEmpty()&& level.isNotEmpty()) {
            val coupon = hashMapOf(
                "name" to name,
                "level" to level,
                "img" to imageUrl, // Agregar la URL de la imagen
            )
            firestore.collection("digimon")
                .add(coupon)
                .addOnSuccessListener {
                    Toast.makeText(this, "Agregar correctamente el id: ${it.id}", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "No se agrego el elemno", Toast.LENGTH_SHORT).show()
                }
        }
    }
}