package com.example.johw.metalgearpachinko;

public class Roda extends Thread {

    interface RodaListener{
        void novaImagem(int resourceID);
    }

    private static int[] imagensSlots = {R.drawable.militariessanfrontiers_logo, R.drawable.outerheaven_logo, R.drawable.foxhound_logo, R.drawable.diamonddogs_logo, R.drawable.metalgear_logo};
    public int indiceAtual;
    private RodaListener rodaListener;
    private long duracaoDoQuadro;
    private long iniciaEm;
    private boolean isIniciado;

    public Roda(RodaListener rodaListener, long duracaoDoQuadro, long iniciaEm){
        this.rodaListener = rodaListener;
        this.duracaoDoQuadro = duracaoDoQuadro;
        this.iniciaEm = iniciaEm;
        indiceAtual = 0;
        isIniciado = true;
    }

    public void proximaImagem(){
        indiceAtual++;

        if(indiceAtual == imagensSlots.length){
            indiceAtual = 0;
        }
    }

    @Override
    public void run(){
        try{
            Thread.sleep(iniciaEm);
        } catch (InterruptedException e){

        }

        while(isIniciado){
            try {
                Thread.sleep(duracaoDoQuadro);
            } catch (InterruptedException e){

            }

            proximaImagem();

            if(rodaListener != null){
                rodaListener.novaImagem(imagensSlots[indiceAtual]);
            }
        }
    }

    public void paraDeRodar(){
        isIniciado = false;
    }
}
