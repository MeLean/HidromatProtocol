package bg.hidromat.hidromatprotocol.starting_screen;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import bg.hidromat.hidromatprotocol.R;
import bg.hidromat.hidromatprotocol.db.entities.Protocol;

public class ProtocolRecyclerAdapter extends RecyclerView.Adapter<ProtocolRecyclerAdapter.ProtocolViewHolder> {
    private List<Protocol> protocols = new ArrayList<>();

    @NonNull
    @Override
    public ProtocolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.protocol_present_item, parent, false);
        return new ProtocolViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProtocolViewHolder holder, int position) {
        Protocol protocol = protocols.get(position);

        holder.name.setText(protocol.getName());
        holder.signerClient.setText(makeTextFromSigners(protocol.getSignersByClient()));
        holder.signerFirm.setText(makeTextFromSigners(protocol.getSignersByFirm()));
    }

    private String makeTextFromSigners(String[] signers){
        StringBuilder stringBuilder = new StringBuilder();

        for (String signer:signers) {
            stringBuilder.append(signer).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public int getItemCount() {
        return protocols.size();
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
        notifyDataSetChanged();
    }

    class ProtocolViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextInputEditText signerClient;
        private TextInputEditText signerFirm;

        ProtocolViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.protocol_name);
            signerClient = itemView.findViewById(R.id.sing_by_client_text);
            signerFirm = itemView.findViewById(R.id.sing_by_firm_text);
        }

    }
}
