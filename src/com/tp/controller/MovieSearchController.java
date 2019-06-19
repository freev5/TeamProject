package com.tp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.service.MDService;
import com.tp.service.RateService;
import com.tp.service.WishListService;
import com.tp.util.tpUtil;
import com.tp.vo.MD;
import com.tp.vo.MovieOutput;
import com.tp.vo.Rate;
import com.tp.vo.WishList;

public class MovieSearchController implements Controller{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		ServletContext sc = req.getServletContext();
		HttpSession ss = req.getSession();
		String id = (String) ss.getAttribute("Loginid");
		String Loging = (String)ss.getAttribute("Loging");
		if(id==null) {
			id="guest";
		}
		if(Loging==null){
			req.setAttribute("result", "�α��� �� �̿��Ͻ� �� �ֽ��ϴ�.");
			//resp.sendRedirect("Login.jsp");
			HttpUtil.forward(req, resp, "/Login.jsp");
		}
		
		//���ڵ�
		req.setCharacterEncoding("UTF-8");
    	//req ��
    	//String text = (String)sc.getAttribute("MovieSearch");
    	//int display = Integer.parseInt((String)sc.getAttribute("MovieDisplay"));
    	String text = (String)req.getParameter("MovieSearch");
    	int display = Integer.parseInt((String)req.getParameter("MovieDisplay"));
		
    	RateService rservice = RateService.getInstance();
		ArrayList<Rate> rate = rservice.RateList(id);
		ss.setAttribute("rlist", rate);
		
		WishListService wservice = WishListService.getInstance();
		ArrayList<WishList> wish = wservice.WishList(id);
		ss.setAttribute("wlist", wish);
		//�⺻ ��
    	String data="";
    	String path="";
    	
    	//���� ��ü&�ʱ�ȭ
    	MD[] movies = new MD[display];
    	MovieOutput movieOp = new MovieOutput();
    	for(int i =0;i<display;i++) {
    		movies[i] = new MD();
    	}
    	
    	//NAVER API ���
    	String clientId = "sc7MLUB5SZVOBdI1j0SU";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
    	String clientSecret = "h1l4Ce_WqP";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        try {
            String query = URLEncoder.encode(text, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query + "&display=" + display + "&"; // json ���
            String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query + "&display=" + display + "&"; // xml ���
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"utf-8"));
            }
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            con.disconnect();
            
            data = sb.toString();
            String[] movie;//�޾ƿ� ��ȭ
            movie = data.split("\"");
            
            //DB��ȭ ��� ��������
            MDService service = MDService.getInstance();
            ArrayList<MD> md = service.MDList();
            
            //�ܼ� Ȯ�ο�
            System.out.print("[�˻��� : "+text+" / ���� : "+display+"]\n");
            System.out.println(data);
            movies[0].setMtitle("start");
            
            
            System.out.println("DB�� ����� ��ȭ ���� : "+md.size());
            int j = 0;
            int check = 0;
            for (int i = 0; i < movie.length; i++) {
            	if (movie[i].equals("display")){
                    movieOp.setDisplay(movie[i+1].replaceAll("[:, ]", ""));
                }
            	
            	if (movie[i].equals("title")){
                    movies[j].setMtitle(movie[i+2].replaceAll("[<b></b>&]", ""));
                }
            	if (movie[i].equals("link")){
            		movies[j].setLink(movie[i+2]);
                }
            	if (movie[i].equals("link")){//��ũ�� ������ �ڵ尪�� ���ͼ� ��ȭid�� ���� link�� ��
            		movies[j].setMcode(Integer.parseInt(movie[i+2].replaceAll("[https://movie.naver.com/movie/bi/mi/basic.nhn?code=]", "")));
                }
            	if (movie[i].equals("image")){
            		//������ ���� �� ��ü�̹��� ����
            		if(movie[i+2].equals("")) {
            			movies[j].setImage("images/content_not.jpg");
            		}
            		else {
            		movies[j].setImage(movie[i+2]);
            		}
                }
            	if (movie[i].equals("subtitle")){
            		movies[j].setSubtitle(movie[i+2].replaceAll("[<b></b>]", " "));
                }
            	if (movie[i].equals("pubDate")){
            			movies[j].setPubDate(movie[i+2]);       		
                }
            	if (movie[i].equals("director")){
            		if(movie[i+2].equals("")) {movies[j].setDirector(" ");}
            		else {movies[j].setDirector(movie[i+2].replaceAll("[| ]", " "));}
                }
            	if (movie[i].equals("actor")){
            		movies[j].setActor(movie[i+2].replaceAll("[| ]", ","));
                }
            	if (movie[i].equals("userRating")){
            		if(movie[i+2].equals("")) {movies[j].setUserRating(" ");}
            		else {movies[j].setUserRating(movie[i+2]);}
            		for(int t=0;t<md.size();t++) {
                    	if (md.get(t).getMtitle().equals(movies[j].getMtitle())){
                    		check=1;
                    		break;
                    		}
            		}
            		if(check!=1) {
                		service.MDInsert(movies[j]);
                		}
            		else {
            			check=0;
            		}
                    j++;
                }
            	
            		}
            md = service.MDList();
            sc.setAttribute("MD", md);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        sc.setAttribute("mlist", movies);
        sc.setAttribute("movies", movies);
        sc.setAttribute("movieOp", movieOp);
        
        path = "/SearchPage.jsp";
        HttpUtil.forward(req, resp, path);
		}
        //System.out.println("ù��° �̹��� : " + movie[0].getImage());
    }