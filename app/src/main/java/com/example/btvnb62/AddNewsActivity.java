package com.example.btvnb62;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewsActivity extends AppCompatActivity {

    private EditText edtTitle;
    private EditText edtURLanh;
    private EditText edtURL ;

    private static final int REQUEST_IMAGE_PICK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_add);

        edtTitle = findViewById(R.id.edtTitle);
        edtURLanh = findViewById(R.id.edtURLanh);
        edtURL = findViewById(R.id.edtURL);
        Button btnThemanh = findViewById(R.id.btnThemanh);
        btnThemanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở thư viện ảnh để chọn ảnh từ bộ nhớ
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, REQUEST_IMAGE_PICK);
            }
        });

        Button btnXacnhan = findViewById(R.id.btnXacnhan);
        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String title = edtTitle.getText().toString().trim();
                String imageUrl = edtURLanh.getText().toString().trim();
                String Url = edtURL.getText().toString().trim();
                // Kiểm tra xem có dữ liệu hợp lệ không
                if (!title.isEmpty() && !imageUrl.isEmpty()) {
                    // Nếu dữ liệu hợp lệ, tạo Intent để truyền dữ liệu về Activity chính
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("NEWS_TITLE", title);
                    resultIntent.putExtra("NEWS_IMAGE_URL", imageUrl);
                    resultIntent.putExtra("NEWS_URL", Url);
                    // Đặt kết quả thành thành công và trả về kết quả
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Kết thúc Activity thêm tin tức
                } else {
                    // Xử lý nếu dữ liệu không hợp lệ
                    // Ví dụ: Hiển thị thông báo lỗi
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_PICK && data != null) {
                // Xử lý khi chọn ảnh từ thư viện thành công
                Uri selectedImage = data.getData();
                // Hiển thị URL của ảnh vào EditText
                edtURLanh.setText(selectedImage.toString());
                // Cho phép người dùng copy URL
                edtURLanh.setEnabled(true);
            }
        }
    }
}
