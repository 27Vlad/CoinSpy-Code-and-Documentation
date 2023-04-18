package com.project.CryptoTracker.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

import org.apache.tomcat.util.json.JSONParser;
import org.assertj.core.api.InputStreamAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CryptoTracker.daos.AssetsDao;
import com.project.CryptoTracker.daos.UserRepository;
import com.project.CryptoTracker.models.Asset;
import com.project.CryptoTracker.models.AssetData;
import com.project.CryptoTracker.models.Coin;
import com.project.CryptoTracker.models.User;



@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	public AssetsDao dao;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	// @GetMapping("/users")
	// public String listUsers(Model model) {
	// 	List<User> listUsers = userRepo.findAll();
	// 	model.addAttribute("listUsers", listUsers);
		
	// 	return "users";
	//}

		public String getPrice(String coin) throws IOException, SQLException{

			URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids="+coin+"&vs_currencies=usd");

			String price = "";

			try (InputStream input = url.openStream()) {
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader reader = new BufferedReader(isr);
				StringBuilder json = new StringBuilder();
				int c;
				while ((c = reader.read()) != -1) {
					json.append((char) c);
				}

				JSONObject jsonobj = new JSONObject(json.toString());
				System.out.println(jsonobj.toString());

				 price = Integer.toString(jsonobj.getJSONObject(coin).getInt("usd"));

				//Object jsonobj2 = jsonobj.get(coin);
						
			}

			return price;
		}
			

		@GetMapping("/market")
		public String market(Model model,Principal principal) throws IOException, SQLException {

			System.out.println(principal.getName());
			List <Asset> assets  = dao.displayList(principal);
			
			

			List<Coin> coinList = new ArrayList<>();

			//Public API URL generated using the website filters and variables
			URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false");
			try (InputStream input = url.openStream()) {

				//reading the output
				InputStreamReader isr = new InputStreamReader(input);
				BufferedReader reader = new BufferedReader(isr);
				StringBuilder json = new StringBuilder();
				int c;
				while ((c = reader.read()) != -1) {
					json.append((char) c);
				}

				//the data is retrieved as an JSON array because it is in []
				JSONArray jsonArr = new JSONArray(json.toString());

				for (int i =0 ; i <jsonArr.length(); i ++){
					//The arraylist stores JSON objects
					System.out.println(jsonArr.getJSONObject(i).get("id"));

					String symbol = jsonArr.getJSONObject(i).get("symbol").toString();
					String name = jsonArr.getJSONObject(i).get("name").toString();
					String image = jsonArr.getJSONObject(i).get("image").toString();
					String price = jsonArr.getJSONObject(i).get("current_price").toString();
					String marketCap = jsonArr.getJSONObject(i).get("market_cap").toString();
					String priceChangePercentage = jsonArr.getJSONObject(i).get("price_change_percentage_24h").toString();
					String circulatingSupply = jsonArr.getJSONObject(i).get("circulating_supply").toString();

					Coin currentCoin = new Coin(symbol.toUpperCase(), name, image, price, marketCap, priceChangePercentage, circulatingSupply);
					
					coinList.add(currentCoin);
					
				}

				model.addAttribute("coinList", coinList);
							
			}

			return "market";
	}


	@GetMapping("/users")
		public String portfolio(Model model,Principal principal) throws IOException, SQLException {

			System.out.println(principal.getName());
			List <Asset> assets  = dao.displayList(principal);
			List <Double> values  = new ArrayList<>();
			Double total=0d;
			Double totalnow=0d;		
			
			//String newww = getPrice(assets.get(i).getName());

			for (int i =0 ;i <assets.size();i++ ){
				assets.get(i).setValueMade(assets.get(i).getPrice()* assets.get(i).getAmount());;
				
				Double newprice = Double.parseDouble(getPrice(assets.get(i).getName()).toString());
				System.out.println(newprice);
				assets.get(i).setValueNow(newprice*assets.get(i).getAmount());

				
				totalnow += (newprice* assets.get(i).getAmount());
				total += (assets.get(i).getPrice()* assets.get(i).getAmount());

				assets.get(i).setValueMade(assets.get(i).getValueNow()-assets.get(i).getValueMade());
			}

			Double difference= totalnow-total;
				

			model.addAttribute("assets", assets);
			model.addAttribute("total", totalnow);
			model.addAttribute("difference", difference);

			return "users";
		}

	@GetMapping("/addAsset")
	public ResponseEntity<?> addAsset(@RequestParam("coin") int coin,@RequestParam("amount") Double amount,@RequestParam("price") Double price,Model model,Principal principal) throws IOException, SQLException {
		System.out.println("asdasdasdsadsa");

		Asset newcoin = new Asset();
		if (coin == 1){
			newcoin.setName("bitcoin");
		}else if(coin == 2){
			newcoin.setName("ethereum");

		}else if(coin == 3){
			newcoin.setName("tether");}
		else {
			newcoin.setName("bitcoin");}
		
	
		newcoin.setPrice(price);
		newcoin.setAmount(amount);
		newcoin.setEmail(principal.getName());


		dao.addAssert(newcoin);

		return new ResponseEntity<>("asdas",HttpStatus.OK);      
	}

	@GetMapping("/error")
    public String handleError() {
        // handle the error and return the appropriate view
        return "error";
    }
	
}

