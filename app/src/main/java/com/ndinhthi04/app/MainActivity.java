package com.ndinhthi04.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    TextView tvKQ;
    FirebaseFirestore database;
    String strKQ = "";
    Context context = this;
    ToDo todo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Inflate the layout XML file

        tvKQ = findViewById(R.id.tv_kq); // Initialize the tvKQ TextView

        // Rest of your code...
        database = FirebaseFirestore.getInstance();
//        select();
//        update();
//        them();
        xoa();
    }

    void them() {
        String id = UUID.randomUUID().toString();
        todo = new ToDo(id, "title 11", "content 11");
        database.collection("TODO")
                .document(id)
                .set(todo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void update() {
        String id = "bb9f6384-6032-4447-8fd1-cb5a0922a673";
        todo = new ToDo(id, "title 11 Update", "Content 11 Update");
        database.collection("TODO")
                .document(id)
                .update(todo.convertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    void xoa() {
        String id = "bb9f6384-6032-4447-8fd1-cb5a0922a673";
        database.collection("TODO").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ArrayList<ToDo> select() {
        ArrayList<ToDo> list = new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            strKQ = "";
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                ToDo todo = doc.toObject(ToDo.class);
                                strKQ += "id:" + todo.getId() + "\n";
                                strKQ += "title:" + todo.getTitle() + "\n";
                                strKQ += "content:" + todo.getContent() + "\n";
                            }
                            tvKQ.setText(strKQ);
                        } else {
                            Toast.makeText(context, "Đã select thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return list;
    }
}