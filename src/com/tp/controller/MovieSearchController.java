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
			req.setAttribute("result", "로그인 후 이용하실 수 있습니다.");
			//resp.sendRedirect("Login.jsp");
			HttpUtil.forward(req, resp, "/Login.jsp");
		}
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
    	//req 값
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
		//기본 값
    	String data="";
    	String path="";
    	
    	//무비 객체&초기화
    	MD[] movies = new MD[display];
    	MovieOutput movieOp = new MovieOutput();
    	for(int i =0;i<display;i++) {
    		movies[i] = new MD();
    	}
    	
    	//NAVER API 사용
    	String clientId = "sc7MLUB5SZVOBdI1j0SU";//애플리케이션 클라이언트 아이디값";
    	String clientSecret = "h1l4Ce_WqP";//애플리케이션 클라이언트 시크릿값";
        try {
            String query = URLEncoder.encode(text, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query + "&display=" + display + "&"; // json 결과
            String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query + "&display=" + display + "&"; // xml 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
            } else {  // 에러 발생
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
            String[] movie;//받아온 영화
            movie = data.split("\"");
            
            //DB영화 목록 가져오기
            MDService service = MDService.getInstance();
            ArrayList<MD> md = service.MDList();
            
            //콘솔 확인용
            System.out.print("[검색어 : "+text+" / 개수 : "+display+"]\n");
            System.out.println(data);
            movies[0].setMtitle("start");
            
            
            System.out.println("DB에 저장된 영화 개수 : "+md.size());
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
            	if (movie[i].equals("link")){//링크의 값에서 코드값만 따와서 영화id로 지정 link와 비교
            		movies[j].setMcode(Integer.parseInt(movie[i+2].replaceAll("[https://movie.naver.com/movie/bi/mi/basic.nhn?code=]", "")));
                }
            	if (movie[i].equals("image")){
            		//포스터 없을 시 대체이미지 지정
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
        //System.out.println("첫번째 이미지 : " + movie[0].getImage());
    }