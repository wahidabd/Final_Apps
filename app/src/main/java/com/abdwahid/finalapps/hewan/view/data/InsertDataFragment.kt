package com.abdwahid.finalapps.hewan.view.data

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentInsertDataBinding
import com.abdwahid.finalapps.hewan.model.DataCategory
import com.abdwahid.finalapps.hewan.utils.FilePath
import com.abdwahid.finalapps.hewan.utils.initPermission
import com.abdwahid.finalapps.hewan.utils.persistImage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import kotlin.random.Random

class InsertDataFragment : Fragment(), View.OnClickListener {

    private lateinit var adapter: ArrayAdapter<DataCategory>
    private var image_path: String? = null
    private lateinit var viewModel: DataViewModel
    private lateinit var _binding: FragmentInsertDataBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInsertDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DataViewModel::class.java)

        binding.topAppBar.title = "Input Data"
        binding.topAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        //Back arrow
        binding.topAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        //permission
        activity?.initPermission()

        binding.btnSaveData.setOnClickListener(this)
        binding.camera.setOnClickListener(this)
        binding.gallery.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v){
            binding.camera -> {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 101)
            }

            binding.gallery -> {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 102)
            }

            binding.btnSaveData -> {
                val category_id = binding.inputDataCategory.editText?.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val title = binding.inputTitleData.editText?.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
                val desc = binding.inputDescData.editText?.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())

                val file = File(image_path)
                val requestFile: RequestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, requestFile)

                    viewModel.insertData(category_id, title, desc, body).observe(this, Observer {
                        Toast.makeText(context, "Berhasil ditambah", Toast.LENGTH_SHORT).show()

                        val fragment = DataFragment()
                        fragmentManager?.beginTransaction()?.apply {
                            replace(R.id.frame_hewan, fragment, DataFragment::class.java.simpleName)
                            addToBackStack(null)
                            commit()
                        }
                    })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val bmp = data?.extras?.get("data") as Bitmap
            binding.inputImageData.setImageBitmap(bmp)

            val random = Random.nextInt(0, 999999)
            val name_file ="PurchaseImage$random"

            image_path = activity?.persistImage(bmp, name_file)

        } else if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            binding.inputImageData.setImageURI(data?.data)

            image_path = context?.let { data?.data?.let { it1 -> FilePath.getPath(it, it1) } }
        }
    }
}