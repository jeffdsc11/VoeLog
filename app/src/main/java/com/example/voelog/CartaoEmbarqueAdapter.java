package com.example.voelog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CartaoEmbarqueAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<CartaoEmbarque> cartaoEmbarqueList;


    public CartaoEmbarqueAdapter(Context context, int layout,ArrayList<CartaoEmbarque> cartaoEmbarqueList) {
        this.context = context;
        this.layout = layout;
        this.cartaoEmbarqueList = cartaoEmbarqueList;
    }

    public int getCount() {
        return cartaoEmbarqueList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartaoEmbarqueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        TextView txtorigem, txtdestino,txtida,txtvolta,txtStatus,txtCodigoID,txtadulto,txtcrianca,txtbebe;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        ViewHolder holder = new ViewHolder();
        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.txtorigem = (TextView) row.findViewById(R.id.txtorigem);
            holder.txtdestino = (TextView) row.findViewById(R.id.txtdestino);
            holder.txtida = (TextView)row.findViewById(R.id.txtida);
            holder.txtvolta = (TextView) row.findViewById(R.id.txtvolta);
            holder.txtStatus = (TextView) row.findViewById(R.id.txtStatus);
            holder.txtCodigoID = (TextView)row.findViewById(R.id.txtCodigoID);
            holder.txtadulto=(TextView)row.findViewById(R.id.txtAdulto);
            holder.txtcrianca=(TextView)row.findViewById(R.id.txtCrianca);
            holder.txtbebe=(TextView)row.findViewById(R.id.txtBebe);
            row.setTag(holder);
        }else{
            holder = (ViewHolder) row.getTag();
        }
         CartaoEmbarque cartaoEmbarque = cartaoEmbarqueList.get(position);

        holder.txtorigem.setText(cartaoEmbarque.getOrigem());
        holder.txtdestino.setText(cartaoEmbarque.getDestino());
        holder.txtida.setText(cartaoEmbarque.getIda());
        holder.txtvolta.setText(cartaoEmbarque.getVolta());
        holder.txtStatus.setText(cartaoEmbarque.getStatus());
        holder.txtCodigoID.setText(cartaoEmbarque.getIdentificador());
        holder.txtadulto.setText(cartaoEmbarque.getAdulto());
        holder.txtcrianca.setText(cartaoEmbarque.getCrianca());
        holder.txtbebe.setText(cartaoEmbarque.getBebe());


        return row;
    }
}
