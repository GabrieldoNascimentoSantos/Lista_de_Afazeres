package br.senai.sp.cotia.listadeafazeres.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.listadeafazeres.R;
import br.senai.sp.cotia.listadeafazeres.adapter.TarefaAdapter;
import br.senai.sp.cotia.listadeafazeres.database.AppDatabase;
import br.senai.sp.cotia.listadeafazeres.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.listadeafazeres.model.Tarefa;

public class PrincipalFragment extends Fragment {
    private FragmentPrincipalBinding binding;
    // variável para database
    private AppDatabase database;
    // variável para o Adapter
    private TarefaAdapter adapter;
    // variável para lista
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);
        //clique no botão de adicionar tarefa
        binding.btnAddTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_fragmentPrincipal_to_fragment);
        });
        // instancia o database
        database = AppDatabase.getDatabase(getActivity());

        // vai definir o layout manager do recycler view
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        //executa a async task
        new ReadTarefa().execute();

        // retorna a view raiz do binding
        return binding.getRoot();
    }
    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{


        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            //Guarda na variável tarefas, as tarefas do banco de dados
            tarefas = database.getTarefaDao().getAll();
            //retorna a lista de tarefas
            return  tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            // instancia o adpter
            adapter = new TarefaAdapter(tarefas, getActivity(), listenerTarefa);
            // Aplicar o adapter no recyclerview
            binding.recyclerTarefas.setAdapter(adapter);
        }
    }

    // implementação da interface onTarefaClickListener
    private TarefaAdapter.OnTarefaClickListener listenerTarefa = (view, tarefa) -> {
        // variável para Transportar a tarefa (pacote)
        Bundle bundle = new Bundle();
        // "pendurar" a tarefa no pacote
        bundle.putSerializable("tarefa", tarefa);
        // navega para o próximo fragment enviando o bundle
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_fragmentPrincipal_to_fragmentLista, bundle);
    };
}