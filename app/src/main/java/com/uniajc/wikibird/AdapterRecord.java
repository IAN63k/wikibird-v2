package com.uniajc.wikibird;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecord extends RecyclerView.Adapter<AdapterRecord.HolderRecord>{

    private Context context;
    private ArrayList<Ave> recordsList;

    DBCRUD dbHelper;
    public AdapterRecord(Context context, ArrayList<Ave> recordsList){
        this.context = context;
        this.recordsList = recordsList;
        dbHelper = new DBCRUD(context);
    }

    @NonNull
    @Override
    public HolderRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new HolderRecord(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull HolderRecord holder,final int position) {

        Ave model = recordsList.get(position);
        final String id = model.getId();
        final String name = model.getName();
        final String image = model.getImage();
        final String bio = model.getBio();
        final String country = model.getCountry();
        final String family = model.getFamily();

        holder.nameTv.setText(name);
        holder.descpTv.setText(bio);
        holder.familyTv.setText(family);

        if (image.equals("null")){
            holder.profileIv.setImageResource(R.drawable.ic_person_black);
        }
        else {
            holder.profileIv.setImageURI(Uri.parse(image));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("RECORD_ID", id);
                context.startActivity(intent);
            }
        });

        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreDialog(
                        ""+position,
                        ""+id,
                        ""+name,
                        ""+country,
                        ""+family,
                        ""+bio,
                        ""+image);

            }
        });
    }

    public void showMoreDialog( String position, final String id, final String name, final String country, final String family,
                                final String bio, final String image){
        String [] options = {"Editar", "Eliminar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    Intent intent = new Intent(context, AgregarItem.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("NAME", name);
                    intent.putExtra("COUNTRY", country);
                    intent.putExtra("FAMILY", family);
                    intent.putExtra("BIO", bio);
                    intent.putExtra("IMAGE", image);
                    intent.putExtra("isEditMode", true);
                    context.startActivity(intent);
                }
                else if (which == 1){
                    dbHelper.deleteData(id);
                    ((Explorer)context).onResume();
                }

            }

        });
        builder.create().show();
    }


    @Override
    public int getItemCount() {
        return recordsList.size();
    }

    class HolderRecord extends RecyclerView.ViewHolder{
        ImageView profileIv;
        TextView nameTv, familyTv, descpTv;
        ImageButton moreBtn;
        public HolderRecord(@NonNull View itemView){
            super(itemView);

            profileIv = itemView.findViewById(R.id.profileIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            familyTv = itemView.findViewById(R.id.familyTv);
            descpTv = itemView.findViewById(R.id.descpTv);
            moreBtn = itemView.findViewById(R.id.moreBtn);

        }
    }
}
