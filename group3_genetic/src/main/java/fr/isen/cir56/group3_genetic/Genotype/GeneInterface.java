package fr.isen.cir56.group3_genetic.Genotype;

import java.io.Serializable;

public interface GeneInterface<DataType> extends Serializable {
	
	void setData(DataType data);
	DataType getData();
	
}