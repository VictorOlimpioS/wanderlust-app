package br.ufrpe.wanderlustapp.pratoTipico.gui;

import br.ufrpe.wanderlustapp.pratoTipico.dominio.PratoTipico;

public interface OnItemClickListener {
    void  onItemClick(PratoTipico pratoTipico, int posicao);
}
