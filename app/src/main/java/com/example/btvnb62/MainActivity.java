package com.example.btvnb62;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddNews;
    private NewsAdapter newsAdapter;

    // Khai báo danh sách tin tức (có thể thay thế bằng danh sách từ API hoặc database)
    private List<TinTuc> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewNews);
        fabAddNews = findViewById(R.id.fabThem);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(newsAdapter);

        // Xử lý sự kiện click trên FloatActionButton để thêm tin tức mới
        fabAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý thêm tin tức mới
                Intent addNewsIntent = new Intent(MainActivity.this, AddNewsActivity.class);
                startActivityForResult(addNewsIntent, 3001);
                // Ví dụ: Mở màn hình thêm tin tức mới và xử lý dữ liệu nhập vào
            }
        });


        // Xử lý sự kiện click trên mỗi item trong RecyclerView
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String url) {
               /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+url));
                startActivity(intent);*/
                TinTuc clickedItem = newsList.get(position);
                String newsUrl = clickedItem.getUrl();
                if (!newsUrl.startsWith("https://")) {
                    newsUrl = "https://" + newsUrl;
                }
                // Tạo Intent để mở WebViewActivity
                Intent webViewIntent = new Intent(MainActivity.this, WebViewActivity.class);
                // Truyền URL của tin tức được nhấn để mở trong WebViewActivity
                webViewIntent.putExtra("NEWS_URL",newsUrl);
                startActivity(webViewIntent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3001 && resultCode == RESULT_OK && data != null) {
            // Xử lý dữ liệu trả về từ Activity thêm tin tức
            String newsTitle = data.getStringExtra("NEWS_TITLE");
            String newsImageUrl = data.getStringExtra("NEWS_IMAGE_URL");
            String newsUrl = data.getStringExtra("NEWS_URL");
            // Tạo đối tượng TinTuc mới từ dữ liệu nhận được
            TinTuc tinTuc = new TinTuc(newsImageUrl, newsTitle, newsUrl);
            // Thêm tin tức vào danh sách
            newsList.add(tinTuc);
            // Cập nhật RecyclerView bằng cách thông báo cho Adapter đã thay đổi dữ liệu
            newsAdapter.notifyDataSetChanged();
        }
    }

}