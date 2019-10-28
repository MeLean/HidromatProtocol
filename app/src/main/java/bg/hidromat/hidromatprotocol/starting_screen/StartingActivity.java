package bg.hidromat.hidromatprotocol.starting_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import bg.hidromat.hidromatprotocol.R;
import bg.hidromat.hidromatprotocol.db.entities.Protocol;
import bg.hidromat.hidromatprotocol.view_models.HidromatViewModel;

public class StartingActivity extends AppCompatActivity {
    private HidromatViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        RecyclerView protocolRecycler = findViewById(R.id.protocol_titles_recycler);
        protocolRecycler.setLayoutManager(new LinearLayoutManager(this));
        protocolRecycler.setHasFixedSize(true);

        final ProtocolRecyclerAdapter adapter = new ProtocolRecyclerAdapter();

        protocolRecycler.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(HidromatViewModel.class);
        viewModel.getAllProtocols().observe(this, new Observer<List<Protocol>>() {
            @Override
            public void onChanged(List<Protocol> protocols) {
                adapter.setProtocols(protocols);
            }
        });
    }
}
