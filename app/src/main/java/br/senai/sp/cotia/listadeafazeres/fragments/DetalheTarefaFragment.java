package br.senai.sp.cotia.listadeafazeres.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.cotia.listadeafazeres.R;
import br.senai.sp.cotia.listadeafazeres.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.listadeafazeres.model.Tarefa;


public class DetalheTarefaFragment extends Fragment {
    // variável para a tarefa a ser detalhada
    private Tarefa tarefa;

    private FragmentDetalheTarefaBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
       binding = FragmentDetalheTarefaBinding.inflate(inflater, container, false);
       // veriffica se existe algo sendo passado no bundle
        if (getArguments() != null){
            // recupera a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            // popula os campos os campos com as informações da tarefa
            binding.detalheTitulo.setText(tarefa.getTitulo());
            binding.detalheDescricao.setText(tarefa.getDescricao());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            binding.detalheData.setText(formatador.format(tarefa.getDataPrevista()));
            if (tarefa.isConcluida()){
                binding.detalheTitulo.setBackgroundColor(getResources().getColor(R.color.verde));
            }else{
                binding.detalheTitulo.setBackgroundColor(getResources().getColor(R.color.amarelo));
            }
        }
        // retorna a view raiz do binding
        return binding.getRoot();
    }
}