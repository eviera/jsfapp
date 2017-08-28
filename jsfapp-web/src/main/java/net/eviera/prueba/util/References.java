package net.eviera.prueba.util;

import org.apache.commons.collections.map.MultiValueMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Crea una tabla de referencias para una sesion en uso (para mantener
 * objetos dentro de una sesion, y pasarlos entre los distintos forms).
 * Los cambios que sufran estos objetos se persistiran.
 * @author esteban@sdd
 * @date   15/08/2001
 */
public class References implements Serializable {

	private static final long serialVersionUID = -2790458727850452466L;
	
	private Map<String, Object>      references   = new HashMap<String, Object>();        //Tiene: key1->objecto, key2->objeto, key3->objeto
	private MultiValueMap  keys   = new MultiValueMap (); //Tiene  hashObject->{key1, key2}, hashObjeto->key3
	private long keyNumber   = 0;
	
	private static long maxKeyNumber   = 10; // Para analizar que tan largas son las references....

	public References() {}

	public Map<String, Object> getValues(){
		return references;
	}
	
	public void setValues(Map<String, Object> newValues){
		references = newValues;
	}
	
	private long getNewKeyNumber() {
		long newKey = ++keyNumber;
		if (newKey > maxKeyNumber) {
			maxKeyNumber = newKey;
		}
		return newKey;
	}
	public String put(Object o) {

		if(o != null) {
			int oKey = o.hashCode();
			//Si no lo tengo, lo agrego
			if(!keys.containsKey(oKey)) {
				String refKey = Long.toString(getNewKeyNumber());
				references.put(refKey, o);
				keys.put(oKey, refKey);
				return refKey;
			} else {
			    //Ya tengo la key. Tengo que ver si es un update del objeto actual, o si justo es otro con el mismo hash
				Collection<String> existingRefKeys = keys.getCollection(oKey);
				for (String existingRefKey:existingRefKeys) {
					Object existingO = references.get(existingRefKey);
					if (o == existingO) { //Ojo! Vamos por == no por equals, porque equals me repite cosas....
						//Ya lo tenia, devuelvo la key actual
						return existingRefKey;
					}
				}
				//Si llegue aca, tengo un caso de dos objectos con mismo hash, le genero su propia key al 2do y lo guarfo
				String refKey = Long.toString(getNewKeyNumber());
				references.put(refKey, o);
				keys.put(oKey, refKey);
				return refKey;
			}
		}
		return null;
	}

	public Object get(String key) {
		Object o = null;

		if(key != null) {
			o = references.get(key);
		}

		return o;
	}

	public void remove(Object o) {
		if (o != null) {
			int oKey = o.hashCode();
			if (keys.containsKey(oKey)) {
				List<String> keysToRemove = new ArrayList<String>();
				Collection<String> existingRefKeys = keys.getCollection(oKey);
				for (String existingRefKey:existingRefKeys) {
					Object existingO = references.get(existingRefKey);
					if (o.equals(existingO)) {
						//No se remueven aqui las keys directamente porque da ConcurrentModificationException, en cambio le las guarda para remover luego
						keysToRemove.add(existingRefKey);
					}
				}
				for (String keyToRemove : keysToRemove) {
					keys.remove(oKey, keyToRemove);
					references.remove(keyToRemove);
				}
			}
		}
    }

	public void clear() {
		keyNumber = 0;
		references.clear();
        keys.clear();
	}

	
	public String toString(){
		return references.toString();
	}

}
