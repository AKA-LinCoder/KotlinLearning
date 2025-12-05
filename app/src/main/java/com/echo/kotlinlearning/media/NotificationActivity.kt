package com.echo.kotlinlearning.media

import android.annotation.SuppressLint
import android.app.ComponentCaller
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityNotificationBinding
import com.echo.kotlinlearning.runtimepermission.RunTimePermissionActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.jvm.Throws

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding
    lateinit var  manager: NotificationManager

//    //拍照后的图片文件
    private lateinit var photoFile: File
//    //展示图片的imageview
//    private lateinit var ivPhoto: ImageView
//
    //相机申请权限code
    private val REQUEST_CAMERA_PERMISIION = 1001

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(
            NotificationChannel(
                "channel1",
                "channel",
                NotificationManager.IMPORTANCE_HIGH
            )
        )

        binding.btnSend.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                requestPermission(android.Manifest.permission.POST_NOTIFICATIONS
                ) { sendNotification() }
//                if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),1)
//                }else {
//                    sendNotification()
//                }
            }else{
                sendNotification()
            }

        }
        binding.btnOpenCamera.setOnClickListener {
            requestPermission(android.Manifest.permission.CAMERA){
                Toast.makeText(this,"camera",Toast.LENGTH_SHORT).show()
                takePhoto()
            }

        }

    }

    private fun takePhoto(){

        try {
            photoFile = createImageFile()
            val photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"$packageName.fileprovider",photoFile)
            }else {
                Uri.fromFile(photoFile)
            }
            //启动相机
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
            startActivityForResult(intent,1002)
        }catch (e: IOException){

        }
    }


    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File{
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()).toString()
        val imageFileName = "JPEG_${timeStamp}_"
        val storageDir = cacheDir.resolve("images")
        if(!storageDir.exists()) storageDir.mkdirs()
        return File.createTempFile(imageFileName,".jpg",storageDir)
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        super.onActivityResult(requestCode, resultCode, data, caller)
        if (resultCode == RESULT_OK&&requestCode == 1002){
            binding.ivCamera.setImageURI(Uri.fromFile(photoFile))
        }
    }



    fun requestPermission(permission:String,success:()-> Unit){
        if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(permission),1001)
        }else {
            success()
        }
    }

    fun sendNotification() {
        val intent = Intent(this,RunTimePermissionActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "channel1")
            .setContentTitle("通知标题")
            .setContentText("通知内容")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        manager.notify(1,notification)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when (requestCode) {
            1 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限被授予
                    sendNotification()
                }else{
                    //权限被拒绝
                    Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show()
                }
            1001 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto()
                }else{
                    //权限被拒绝
                    Toast.makeText(this,"权限被拒绝",Toast.LENGTH_SHORT).show()
                }

        }
    }

}