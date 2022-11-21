package com.uniajc.wikibird;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Explorer extends AppCompatActivity {


    private Button addRecordBtn;
    private RecyclerView recordsRv;
    private DBCRUD dbHelper;
    ActionBar actionBar;
    String orderByNewest = Constants.C_ID + " DESC";
    String currentOrderByStatus = orderByNewest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);


        addRecordBtn = findViewById(R.id.addRecordBtn);
        recordsRv = findViewById(R.id.recordsRv);
        dbHelper = new DBCRUD(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setElevation(0);

        loadRecords(orderByNewest);

        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Iniciar la Activity
                Intent intent = new Intent(Explorer.this, AgregarItem.class);
                intent.putExtra("isEditMode", false);//desea establecer nuevos datos, set false.
                startActivity(intent);
            }
        });
    }

    private void loadRecords(String orderBy) {
        currentOrderByStatus = orderBy;
        AdapterRecord adapterRecord = new AdapterRecord(Explorer.this,
                dbHelper.getAllRecords(orderBy));

        recordsRv.setAdapter(adapterRecord);

    }

    private void searchRecords(String query) {
        AdapterRecord adapterRecord = new AdapterRecord(Explorer.this,
                dbHelper.searchRecords(query));

        recordsRv.setAdapter(adapterRecord);


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecords(currentOrderByStatus);// Refresca o actualiza la lista de registros
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //searchView
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // buscar cuando se hace clic en el botón de búsqueda en el teclado
                searchRecords(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // busca mientras escribes
                searchRecords(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}

