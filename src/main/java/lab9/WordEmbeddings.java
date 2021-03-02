package lab9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class WordEmbeddings {

	public WordEmbeddings() throws NumberFormatException, IOException {
		
		//https://deeplearning4j.konduit.ai/language-processing/word2vec
		
		//Word2Vec w2vModel = WordVectorSerializer.readWordVectors(new File("files_lab9/pizza.embeddings.txt"));
		Word2Vec w2vModel = WordVectorSerializer.readWord2VecModel(new File("files_lab9/pizza.embeddings.bin"));
		//InputStream word2vecmodelFile = new FileInputStream("files_lab9/pizza.embeddings.bin");
		//Word2Vec w2vModel = WordVectorSerializer.readBinaryModel(word2vecmodelFile, false, false);

		
		System.out.println(w2vModel.wordsNearest("pizza", 10));
		System.out.println(w2vModel.similarity("pizza", "food"));
		
	}
	
	
	//Gives an error to load the embeddings from python. Instead use the generated document and compute word embeddings.
	//It will need some work. 
	
	
	public static void main(String[] args) {

		try {
			new WordEmbeddings();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
