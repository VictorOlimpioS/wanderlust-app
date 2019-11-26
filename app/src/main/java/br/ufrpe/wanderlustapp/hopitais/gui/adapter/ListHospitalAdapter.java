package br.ufrpe.wanderlustapp.hopitais.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufrpe.wanderlustapp.R;
import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;

public class ListHospitalAdapter extends RecyclerView.Adapter<ListHospitalAdapter.HospitalViewHolder>{

    private final Context context;
    private final List<Hospitais> hospitais;


    public ListHospitalAdapter(Context context, List<Hospitais> hospitais) {
        this.context = context;
        this.hospitais = hospitais;
    }

    public List<Hospitais> getList(){
        return this.hospitais;
    }


    @Override
    public ListHospitalAdapter.HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        Hospitais hospital = hospitais.get(position);
        holder.vincula(hospital);
    }

    @Override
    public int getItemCount() {
        return hospitais.size();
    }


    public void adiciona(Hospitais hospital){
        hospitais.add(hospital);
        notifyDataSetChanged();
    }

    class HospitalViewHolder extends RecyclerView.ViewHolder {

        private final TextView titulo;
        private final TextView descricao;
        private Hospitais hospital;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_hopsital_nome);
            descricao = itemView.findViewById(R.id.item_hospital_descricao);
        }

        public void vincula(Hospitais hospital) {
            this.hospital = hospital;
            titulo.setText(this.hospital.getNome());
            descricao.setText(this.hospital.getDescricao());

        }
    }
}
