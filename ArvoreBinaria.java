
/* Gabriel De Morais Lacerda
    RA : 117114004
*/

import java.io.*;
import java.util.*;

class No {
    // Modelo No
    public long item;
    public No dir;
    public No esq;
}
//  test
class Arvore {
    // instanciando o No
    private No root;

    public Arvore() {
        root = null;
    }

    public void inserir(long v) {
        final No novo = new No();
        novo.item = v;
        novo.dir = null;
        novo.esq = null;

        if (root == null)
            root = novo;
        else {
            No atual = root;
            No anterior;
            while (true) {
                anterior = atual;
                if (v <= atual.item) {
                    atual = atual.esq;
                    if (atual == null) {
                        anterior.esq = novo;
                        return;
                    }
                } else {
                    atual = atual.dir;
                    if (atual == null) {
                        anterior.dir = novo;
                        return;
                    }
                }
            }
        }

    }

    // pesquisar pela chave No atual
    public No pesquisar(long chave) {
        int contador = 0;

        if (root == null)
            return null;
        No atual = root;
        while (atual.item != chave) {
            if (chave < atual.item) {
                atual = atual.esq;
                contador++;
            } else {
                atual = atual.dir;
                contador++;
            }
            if (atual == null) {
                contador++;
                return null;
            }
        }

        contador(contador);
        return atual;

    }

    void contador(int contador) {
        // console.log(contador);

        System.out.println("Contador de comparacoes:" + contador);
    }

    public No no_sucessor(No apaga) {
        No paidosucessor = apaga;
        No sucessor = apaga;
        No atual = apaga.dir;

        while (atual != null) {
            paidosucessor = sucessor;
            sucessor = atual;
            atual = atual.esq;
        }

        if (sucessor != apaga.dir) {
            paidosucessor.esq = sucessor.dir;
            sucessor.dir = apaga.dir;
        }
        return sucessor;
    }

    // remover retorna boolean
    public boolean remover(long v) {
        if (root == null)
            return false;

        No atual = root;
        No pai = root;
        boolean filho_esq = true;

        while (atual.item != v) {
            pai = atual;
            if (v < atual.item) {
                atual = atual.esq;
                filho_esq = true;
            } else {
                atual = atual.dir;
                filho_esq = false;
            }
            if (atual == null)
                return false;
        }

        if (atual.esq == null && atual.dir == null) {
            if (atual == root)
                root = null;
            else if (filho_esq)
                pai.esq = null;
            else
                pai.dir = null;
        }

        else if (atual.dir == null) {
            if (atual == root)
                root = atual.esq;
            else if (filho_esq)
                pai.esq = atual.esq;
            else
                pai.dir = atual.esq;
        }

        else if (atual.esq == null) {
            if (atual == root)
                root = atual.dir;
            else if (filho_esq)
                pai.esq = atual.dir;
            else
                pai.dir = atual.dir;
        }

        else {
            final No sucessor = no_sucessor(atual);
            if (atual == root)
                root = sucessor;
            else if (filho_esq)
                pai.esq = sucessor;
            else
                pai.dir = sucessor;
            sucessor.esq = atual.esq;
        }

        return true;

        // fechamento method remove
    }

    // fechamento class master
public void caminhar() {
    System.out.print("\n Exibindo em ordem: ");
    inOrder(root);
    System.out.print("\n Exibindo em pos-ordem: ");
    posOrder(root);
    System.out.print("\n Exibindo em pre-ordem: ");
    preOrder(root);
    System.out.print("\n Altura da arvore: " + altura(root));
    System.out.print("\n Quantidade de folhas: " + folhas(root));
    System.out.print("\n Quantidade de Nós: " + contarNos(root));
    if (root != null ) {  // se arvore nao esta vazia
       System.out.print("\n Valor minimo: " + min().item);
       System.out.println("\n Valor maximo: " + max().item);
    }
  }

  public void inOrder(No atual) {
    if (atual != null) {
      inOrder(atual.esq);
      System.out.print(atual.item + " ");
      inOrder(atual.dir);
    }
  }
  
  public void preOrder(No atual) {
    if (atual != null) {
      System.out.print(atual.item + " ");
      preOrder(atual.esq);
      preOrder(atual.dir);
    }
  }
  
  public void posOrder(No atual) {
    if (atual != null) {
      posOrder(atual.esq);
      posOrder(atual.dir);
      System.out.print(atual.item + " ");
    }
  }  
  
  public int altura(No atual) {
     if(atual == null || (atual.esq == null && atual.dir == null))
       return 0;
     else {
   	if (altura(atual.esq) > altura(atual.dir))
   	   return ( 1 + altura(atual.esq) );
   	else
	   return ( 1 + altura(atual.dir) );
     }
  }
  
  public int folhas(No atual) {
    if(atual == null) return 0;
    if(atual.esq == null && atual.dir == null) return 1;
    return folhas(atual.esq) + folhas(atual.dir);
  }
  
  public int contarNos(No atual) {
   if(atual == null)  return 0;
   else return ( 1 + contarNos(atual.esq) + contarNos(atual.dir));
  }

  public No min() {
    No atual = root;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.esq;
    }
    return anterior;
  }

  public No max() {
    No atual = root;
    No anterior = null;
    while (atual != null) {
      anterior = atual;
      atual = atual.dir;
    }
    return anterior;
  }
}

class ArvoreBinaria {

    public void main(String[] args) {
        Scanner ent = new Scanner(System.in);
        Arvore arv = new Arvore();

        // int[] A = { 80, 3, 90, 70, 20, 15, 18, 17 };
        // int[] B = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        // int[] C = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

        int opcao = 0;
        long x;

        System.out.print("\n Programa Arvore binaria");
        do {
            System.out.print("\n***********************************");
            System.out.print("\nEntre com a opcao:");
            System.out.print("\n ----1: Inserir");
            System.out.print("\n ----2: Excluir");
            System.out.print("\n ----3: Pesquisar");
            System.out.print("\n ----4: Exibir");
            System.out.print("\n ----5: Sair do programa");
            System.out.print("\n***********************************");
            System.out.print("\n-> ");
            opcao = ent.nextInt();
            switch (opcao) {
                case 1: {
                    System.out.print("\n Informe o valo -> ");
                    x = ent.nextLong();
                    arv.inserir(x);
                    break;
                }
                case 2: {
                    System.out.print("\n Informe o valor -> ");
                    x = ent.nextLong();
                    if (!arv.remover(x))
                        System.out.print("\n Valor nao encontrado!");
                    ;
                    break;
                }
                case 3: {
                    System.out.print("\n Informe o valor  -> ");
                    x = ent.nextLong();
                    if (arv.pesquisar(x) != null)
                        System.out.print("\n Valor Encontrado");
                    else
                        System.out.print("\n Valor nao encontrado!");
                    break;
                }
                case 4: {
                    arv.caminhar();
                    break;
                }
            } // fim switch
        } while (opcao != 5);
    }
}




