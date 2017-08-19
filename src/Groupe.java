import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implantation d'un groupe de Boule(s) à l'aide d'un HashSet 
 * à utiliser tel quel sans modifier
 * 
 * @author Guy Lapalme
 */
public class Groupe implements Iterable<Boule>{
	private Set<Boule> groupe;
	
	/**
	 * Créer un nouveau groupe
	 */
	Groupe(){
		groupe = new HashSet<Boule>();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return groupe.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * 
	 * Ceci permet d'itérer sur les boules d'un groupe 
	 *      for(Boule b:groupe){...}
	 */
	public Iterator<Boule> iterator(){
		return groupe.iterator();
	}
	
	/**
	 * Retourne le nombre de boules dans le groupe
	 *
	 */
	public int size(){
		return groupe.size();
	}
	
	/**
	 * Vérifie si un groupe est vide ou non
	 */
	public boolean isEmpty(){
		return groupe.isEmpty();
	}
	
	/**
	 * Ajoute une boule à l'ensemble, sans effet si elle y est déjà
	 * @param b : la boule à ajouter au groupe
	 */
	public void add(Boule b){
		groupe.add(b);
	}
	
	/**
	 * Vérifie si une boule est dans le groupe ou non
	 * @param b
	 * @return true si la boule est déjà dans le groupe
	 */
	public boolean contains(Boule b){
		return groupe.contains(b);
	}
	
	/**
	 * Vider le groupe de ses éléments
	 */
	public void clear(){
		groupe.clear();
	}
}
