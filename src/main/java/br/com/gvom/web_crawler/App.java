package br.com.gvom.web_crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String urlProx = "";
    	List<String> urls = new ArrayList<String>();
    	urls.add("https://www.wuxiaworld.com/novel/wu-dong-qian-kun/wdqk-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/battle-through-the-heavens/btth-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/a-will-eternal/awe-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/warlock-of-the-magus-world/wmw-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/skyfire-avenue/sfl-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/coiling-dragon-preview/cdp-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/martial-world/mw-chapter-0");
    	urls.add("https://www.wuxiaworld.com/novel/i-shall-seal-the-heavens/issth-book-1-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/desolate-era/de-book-1-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/heavenly-jewel-change/hjc-book-1-chapter-1-01");
    	urls.add("https://www.wuxiaworld.com/novel/i-reincarnated-for-nothing/irfn-chapter-0");
    	urls.add("https://www.wuxiaworld.com/novel/child-of-light/col-volume-1-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/terror-infinity/ti-vol-1-chapter-1-1");
    	urls.add("https://www.wuxiaworld.com/novel/stellar-transformations/st-book-1-chapter-1");
    	urls.add("https://www.wuxiaworld.com/novel/7-killers/7k-chapter-7");
    	
    	for(String url: urls) {
	    	int urlPart = 1 ;
	    	HtmlPage pageAtual = null;
	    	boolean paginaExiste = true;
	    	
	    	FileWriter arq = null;
			try {
				String[] parts = url.split("/");
				arq = new FileWriter("c:\\Users\\Programação\\Downloads\\"+parts[parts.length-1]+".txt");
				System.out.println("Abriu/Criou o Arquivo "+url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(arq != null) {
		        PrintWriter gravarArq = new PrintWriter(arq);
		    	
		    	WebClient client = new WebClient();  
		    	client.getOptions().setCssEnabled(false);  
		    	client.getOptions().setJavaScriptEnabled(false);
		    	while(paginaExiste) {
			    	try { 
			    		String searchUrl = "";
			    		if(urlPart == 1) {
			    			searchUrl = url;
			    		}else {
			    			searchUrl = "https://www.wuxiaworld.com/" + urlProx;
			    		}
			    	  HtmlPage page = client.getPage(searchUrl);
			    	  pageAtual = page;
			    	}catch(Exception e){
			    		paginaExiste = false;
			    	  e.printStackTrace();
			    	}
			    	
			    	List<HtmlElement> items = (List<HtmlElement>) pageAtual.getByXPath("//div[@class='p-15']") ;  
			    	if(items.isEmpty()){  
			    	  System.out.println("No items found !");
			    	  paginaExiste = false;
			    	}else{
			    		
			    	  HtmlAnchor linkProx = ((HtmlAnchor) pageAtual.getFirstByXPath(".//li[@class='next']/a"));
			    	  
			    	  HtmlElement titulo = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='caption clearfix']"));
			
			    	  HtmlElement conteudo = ((HtmlElement) items.get(0).getFirstByXPath(".//div[@class='fr-view']")) ;
			
			    	  String itemTitulo = titulo.asText();
			    	  String itemConteudo =  conteudo.asText();
			    	  urlProx = linkProx.getHrefAttribute();
			    	  
			    	  gravarArq.printf("Titulo : %s %n_________________________________________%n%n%n  %s %n%n%n%n%n", itemTitulo, itemConteudo);
			    	  //System.out.println( String.format("Titulo : %s \n--------------- (੭ ᐕ)੭*⁾⁾ ---------------\n\nConteudo :\n  %s \n\n\n\n\n", itemTitulo, itemConteudo));
			    	  System.out.println("Capitulo: " + urlPart + " com ulr: "+ pageAtual.getUrl().toString());
			    	  
			    	  urlPart++;

			    	}
		    	}
		    	
		    	try {
					arq.close();
					System.out.println("Concluiu de Gravar o Arquivo "+url);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }
    }
}
