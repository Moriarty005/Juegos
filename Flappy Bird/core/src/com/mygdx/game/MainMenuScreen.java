/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class MainMenuScreen implements Screen {
        final Drop game;
	OrthographicCamera camera;
        
        Texture fondo;
        Texture logo;
        
        public static int highscore;

	public MainMenuScreen(final Drop gam) throws SAXException, IOException {
		game = gam;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
                
                fondo = new Texture(Gdx.files.internal("fondo_main_menu.jpg"));
                
                logo = new Texture(Gdx.files.internal("logo.png"));

                this.leetXML();
	}

	@Override
	public void render(float delta) {
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



            camera.update();
            game.batch.setProjectionMatrix(camera.combined);

            game.batch.begin();
            game.batch.draw(fondo, 0, 0, 800, 480);
            
            game.batch.draw(logo, 175, 600/2);
            game.font.draw(game.batch, "Drop", 350, 600/2);

            game.font.draw(game.batch, "HighScore: " + highscore, 620/2, 400/2);

            game.font.draw(game.batch, "Toca en cualquier lado para comenzar", 200 / 2, 100);
            game.batch.end();

            if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    game.setScreen(new GameScreen(game));
                    dispose();
            }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
        
        public static void convertirAXML(){
            File fichero = new File("Punctuacion.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        

            try{
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();
                Document document = implementation.createDocument(null, "puntuacion", null);
                document.setXmlVersion("1.0"); 
                
                Element raiz = document.createElement("mejorPuntuacion");
                 
                document.getDocumentElement().appendChild(raiz);
                
                crearElemento("HighScore", String.valueOf(MainMenuScreen.highscore), raiz, document);
                
                Source direccion = new DOMSource(document);
             
                Result result = new StreamResult(new java.io.File("Puntuacion.xml"));

                Transformer optimus_prime = TransformerFactory.newInstance().newTransformer();

                optimus_prime.transform(direccion, result);
                
            }catch(Exception e){
             
             System.err.println("Error: "+e);
            }
        }
        
        static void crearElemento(String dato, String valor, Element raiz, Document fichero){
        
            Element elem = fichero.createElement(dato); 
            Text text = fichero.createTextNode(valor); //damos valor
            raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
            elem.appendChild(text); //pegamos el valor
        }
        
        public void leetXML() throws org.xml.sax.SAXException, IOException, SAXException{
                
        XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
        
	GestionarContenido gestor= new GestionarContenido();  
        
	procesadorXML.setContentHandler(gestor);
        
 	InputSource fileXML = new InputSource("Puntuacion.xml");	
        
        procesadorXML.parse(fileXML); 
    }
        
        
    static class GestionarContenido extends DefaultHandler{
        
        public int new_high;
        public String elemento;
        
        public GestionarContenido() {
            super();
            
        }
        
        public void startDocument() {
            System.out.println("Comienzo del Documento XML");
        }
        
        public void endDocument() {
            System.out.println("Final del Documento XML");
        }
        
        public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
            System.out.printf("\t Principio Elemento: %s %n",nombre);
            elemento = nombre;
        }
        
        public void endElement(String uri, String nombre, String nombreC) {
            System.out.printf("\tFin Elemento: %s %n", nombre);
            
        }
        
        @Override
        public void characters(char[] ch, int inicio, int longitud){
               String car=new String(ch, inicio, longitud);
           //quitar saltos de línea	
               car = car.replaceAll("[\t\n]","");	   
               System.out.printf ("\t Caracteres: %s %n", car);	
               //System.out.println("ENTRO!!!!!!!!!!!!!!!!");
               if(elemento == "HighScore"){
                   
                   MainMenuScreen.highscore = Integer.valueOf(car);
               }
        }	
    }
}
