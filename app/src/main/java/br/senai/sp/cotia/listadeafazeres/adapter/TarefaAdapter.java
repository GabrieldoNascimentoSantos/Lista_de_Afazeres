package br.senai.sp.cotia.listadeafazeres.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.cotia.listadeafazeres.R;
import br.senai.sp.cotia.listadeafazeres.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    // lista de tarefas
    private List<Tarefa> tarefas;
    //variável para o Context
    private Context context;
    //variável do tipo Listener
    private OnTarefaClickListener listenerClicktarefa;

    //criar o construtor para receber os valores(lista)
    public TarefaAdapter(List<Tarefa> lista, Context contexto, OnTarefaClickListener listener){
        this.tarefas = lista;
        this.context = contexto;
        this.listenerClicktarefa = listener;
    }
    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);
        //retorna um novo ViewHolder com a view
        return new TarefaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obtem a tarefa pela position
        Tarefa t = tarefas.get(position);
        //exibe o titulo da tarefa no text view
        holder.tvTitulo.setText(t.getTitulo());
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.dataRealizacao);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.verde));
        }else{
            //Calendar.getInstance().getTimeInMillis();
             //Calendar dataAtual;

            holder.tvStatus.setText(R.string.dataAberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.amarelo));

        }

        //formara a data de long para string
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));

        holder.itemView.setOnClickListener(v -> {
            //dispara o listener
            listenerClicktarefa.onClick(v, t);
        });
    }

    @Override
    //retorna a quantidade de elementos a serem exibidos na lista
    public int getItemCount() {
        if(tarefas != null){
            return tarefas.size();
        }
        return 0;
    }

    // Classe ViewHolder Para mapear os componentes do xml
    class TarefaViewHolder extends RecyclerView.ViewHolder{
        // variávei spara acessar os componentes do xml
        TextView tvTitulo, tvData, tvStatus;

        public TarefaViewHolder(View view){
            // chama o construtor da suoer classe
            super(view);
            //passar para as variáveis, os componentesdo XML
            tvTitulo = view.findViewById(R.id.tv_titulo);
            tvData = view.findViewById(R.id.tv_data);
            tvStatus = view.findViewById(R.id.tv_status);
        }
    }

    // interface para o clique na tarefa
    public interface OnTarefaClickListener{
        void onClick(View view, Tarefa tarefa);
    }
}
