package com.example.starcrash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class reportes extends Activity  {
	public int act2=0;
	public int mt2=0;
	public int at3=0;
	public int mt3=0;
	int ind=3;
	public int at=0;
	public int mt=0;
	String[] planets;
	
	int sw=1;
	int sw3=1;
	int sw2=1;
	int cont=0;
	int cont2=0;
	int cont3=0;
	
 Button semanal;
 Button mes;
 Button anual;
 private ListView list ; 
 String[] meses;
 private ArrayAdapter<String> lista ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		
		
		list=(ListView)findViewById(R.id.listar);
		
		
		semanal =(Button)findViewById(R.id.semanal);
		anual =(Button)findViewById(R.id.anual);
		
		
		mes=(Button)findViewById(R.id.mensual);

	 
		semanal.setBackgroundColor(Color.argb(186,18, 214, 236));
		anual.setBackgroundColor(Color.argb(186,18, 214, 236));
		mes.setBackgroundColor(Color.argb(186,18, 214, 236));
				
		mes.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) {
				
				 planets = new String[] { " Reporte Mensual "," ----- " };
				
				
				 meses = new String[] { "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre", "Diciembre"};    
         
            
				
				
		
				
				Parse.initialize(reportes.this, "wxRgc6TqQnkvj51xcf0uyl9IPBbxUnMpqXJrCZmj", "CGLz5cB0cgUNUwiZj9oSTYfVWSqXBn0dbIUHB7UT");
				at=0;
				mt=0;
				act2=0;
				mt2=0;
				at3=0;
				mt3=0;
				sw=1;
				sw3=1;
				sw2=1;
				cont =0;
				ParseQuery<ParseObject> query = ParseQuery.getQuery("maps");
				query.whereEqualTo("nivel", "Leve");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList.size();i++){
				        		
				        		ParseObject l= scoreList.get(i);
				        		Date dt= l.getDate("fecha");
				        		
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList.size();j++){
				        			ParseObject l2= scoreList.get(i);
					        		Date d2t= l2.getDate("fecha");
					        		int m2= dt.getMonth();
					        		int a2=dt.getYear();
				        			
					        		
					        		if(act2==0){
					        			
					        			if (  a==a2) {

					        				cont++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if (act2==a) {


					        				sw2=1;

					        				}
					        			else{
					        				
					        				
					        				if ( a==a2) {

					        					cont++;

					        					sw2=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                                 lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"1");
                                                 planets[ind]= meses[d2t.getMonth()] + "1" + "Nivel " +"1"    ;
					        					  cont=0;
                                                   ind++; 
					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont>0 && sw2==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                  //  lista.add(meses[dt.getMonth()] + " " +cont+1 + " Nivel " +"1");
				                    planets[ind]= meses[dt.getMonth()] + " "+cont+1 + " Nivel " +"1"    ;
				        		
				        			act2=a;
				        			cont=0;
				        			ind++;
				        			}

				        			else {


				        			mt2=0;
				        			act2=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
				
				
				cont2 =0;
				ParseQuery<ParseObject> query2 = ParseQuery.getQuery("maps");
				query2.whereEqualTo("nivel", "Grave");
				query2.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList2, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList2.size();i++){
				        		
				        		ParseObject l= scoreList2.get(i);
				        		Date dt= l.getDate("fecha");
				        
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList2.size();j++){
				        			ParseObject l2= scoreList2.get(i);
					        		Date d2t= l2.getDate("fecha");
					        		int m2= dt.getMonth();
					        		int a2=dt.getYear();
				        			
					        		
					        		if(at3==0){
					        			
					        			if (  a==a2) {

					        				cont2++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if ( at3==a) {


					        				sw3=1;

					        				}
					        			else{
					        				
					        				
					        				if (a==a2) {

					        					cont2++;

					        					sw3=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                           //      lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"2");
                                                 planets[ind]= meses[d2t.getMonth()] + "1" + "Nivel " +"2"    ;
					        					  cont2=0;
                                                  ind++;
					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont2>0 && sw3==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                  //  lista.add(meses[dt.getMonth()] + cont2+1 + "Nivel " +" 2");
				                    planets[ind]= meses[dt.getMonth()] +" "+ cont2+1 + "Nivel " +"2"    ;
				        		
				        			at3=a;
				        			cont2=0;
				        			ind++;
				        			}

				        			else {


				        	
				        			at3=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
				
				
				cont3 =0;
				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("maps");
				query3.whereEqualTo("nivel", "Muy grave");
				query3.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList3, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList3.size();i++){
				        		
				        		ParseObject l= scoreList3.get(i);
				        		Date dt= l.getDate("fecha");
				        
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList3.size();j++){
				        			ParseObject l2= scoreList3.get(i);
					        		Date d2t= l2.getDate("fecha");
					        	
					        		int a2=dt.getYear();
				        			
					        		
					        		if( at==0){
					        			
					        			if (  a==a2) {

					        				cont3++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if (  at==a) {


					        				sw=1;

					        				}
					        			else{
					        				
					        				
					        				if ( a==a2) {

					        					cont3++;

					        					sw=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                              //   lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"3");
                                                 planets[ind]= meses[d2t.getYear()] + "1" + "Nivel " +"3"    ;
                                                 ind++;
					        					  cont3=0;

					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont3>0 && sw==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                    //lista.add(meses[dt.getMonth()] + cont3+1 + " Nivel " +"2");
				                    planets[ind]= meses[dt.getYear()] +" "+ cont3+1 + " Nivel " +"3"    ;
				        		
				        			at=a;
				        			cont3=0;
				        			ind++;
				        			}

				        			else {


				        			mt=0;
				        			at=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
			      ArrayList<String> llist = new ArrayList<String>();  
	               llist.addAll( Arrays.asList(planets) );  


	            lista= new ArrayAdapter<String>(reportes.this, R.layout.simplerow,llist); 
				
				 llist.addAll( Arrays.asList(planets) );  


		        lista= new ArrayAdapter<String>(reportes.this, R.layout.simplerow,llist); 
				list.setAdapter( lista );  
			
			}//end click
			});
		
		
		anual.setOnClickListener(new OnClickListener() 
		{

			@Override
			public void onClick(View arg0) {
				
				
				 planets = new String[] { " Reporte Anual "," ----- " };
				
					
				 meses = new String[] { "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre", "Diciembre"};    
         
				    at=0;
					mt=0;
					act2=0;
					mt2=0;
					at3=0;
					mt3=0;
					sw=1;
					sw3=1;
					sw2=1;
				
				
		
				
				Parse.initialize(reportes.this, "wxRgc6TqQnkvj51xcf0uyl9IPBbxUnMpqXJrCZmj", "CGLz5cB0cgUNUwiZj9oSTYfVWSqXBn0dbIUHB7UT");
				
				cont =0;
				ParseQuery<ParseObject> query = ParseQuery.getQuery("maps");
				query.whereEqualTo("nivel", "Leve");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList.size();i++){
				        		
				        		ParseObject l= scoreList.get(i);
				        		Date dt= l.getDate("fecha");
				        		int m= dt.getMonth();
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList.size();j++){
				        			ParseObject l2= scoreList.get(i);
					        		Date d2t= l2.getDate("fecha");
					        		int m2= dt.getMonth();
					        		int a2=dt.getYear();
				        			
					        		
					        		if(mt2==0 && act2==0){
					        			
					        			if ( m==m2 && a==a2) {

					        				cont++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if ( mt2== m && act2==a) {


					        				sw2=1;

					        				}
					        			else{
					        				
					        				
					        				if ( m==m2 && a==a2) {

					        					cont++;

					        					sw2=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                                 lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"1");
                                                 planets[ind]= meses[d2t.getMonth()] + "1" + "Nivel " +"1"    ;
					        					  cont=0;
                                                   ind++; 
					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont>0 && sw2==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                  //  lista.add(meses[dt.getMonth()] + " " +cont+1 + " Nivel " +"1");
				                    planets[ind]= meses[dt.getMonth()] + " "+cont+1 + " Nivel " +"1"    ;
				        			mt2=m;
				        			act2=a;
				        			cont=0;
				        			ind++;
				        			}

				        			else {


				        			mt2=0;
				        			act2=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
				
				
				cont2 =0;
				ParseQuery<ParseObject> query2 = ParseQuery.getQuery("maps");
				query2.whereEqualTo("nivel", "Grave");
				query2.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList2, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList2.size();i++){
				        		
				        		ParseObject l= scoreList2.get(i);
				        		Date dt= l.getDate("fecha");
				        		int m= dt.getMonth();
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList2.size();j++){
				        			ParseObject l2= scoreList2.get(i);
					        		Date d2t= l2.getDate("fecha");
					        		int m2= dt.getMonth();
					        		int a2=dt.getYear();
				        			
					        		
					        		if(mt3==0 && at3==0){
					        			
					        			if ( m==m2 && a==a2) {

					        				cont2++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if ( mt3== m && at3==a) {


					        				sw3=1;

					        				}
					        			else{
					        				
					        				
					        				if ( m==m2 && a==a2) {

					        					cont2++;

					        					sw3=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                           //      lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"2");
                                                 planets[ind]= meses[d2t.getMonth()] + "1" + "Nivel " +"2"    ;
					        					  cont2=0;
                                                  ind++;
					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont2>0 && sw3==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                  //  lista.add(meses[dt.getMonth()] + cont2+1 + "Nivel " +" 2");
				                    planets[ind]= meses[dt.getMonth()] +" "+ cont2+1 + "Nivel " +"2"    ;
				        			mt3=m;
				        			at3=a;
				        			cont2=0;
				        			ind++;
				        			}

				        			else {


				        			mt3=0;
				        			at3=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
				
				
				cont3 =0;
				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("maps");
				query3.whereEqualTo("nivel", "Muy grave");
				query3.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList3, ParseException e) {
				        if (e == null) {
				        	
				        	for	(int i=0; i<scoreList3.size();i++){
				        		
				        		ParseObject l= scoreList3.get(i);
				        		Date dt= l.getDate("fecha");
				        		int m= dt.getMonth();
				        		int a=dt.getYear();
				        		
				        		
				        		
				        		for(int j=i+1;j<scoreList3.size();j++){
				        			ParseObject l2= scoreList3.get(i);
					        		Date d2t= l2.getDate("fecha");
					        		int m2= dt.getMonth();
					        		int a2=dt.getYear();
				        			
					        		
					        		if(mt==0 && at==0){
					        			
					        			if ( m==m2 && a==a2) {

					        				cont3++;



					        				}
					        			
					        		}
					        		else{
					        			
					        			
					        			if ( mt== m && at==a) {


					        				sw=1;

					        				}
					        			else{
					        				
					        				
					        				if ( m==m2 && a==a2) {

					        					cont3++;

					        					sw=0;

					        					}
					        					else{
					        					//t.row.add([meses[date2.getMonth()],1,"1"]).draw();
                                              //   lista.add(meses[d2t.getMonth()] + "1" + "Nivel " +"3");
                                                 planets[ind]= meses[d2t.getMonth()] + "1" + "Nivel " +"3"    ;
                                                 ind++;
					        					  cont3=0;

					        					}
					        				
					        				
					        				
					        			}
					        			
					        			
					        			
					        			
					        			
					        		}
					        		
					        		
					        		
					        		
					        		
				        			
				        		}
				        		
				        		
				        		if (cont3>0 && sw==0) {
				        			//t.row.add([meses[date.getMonth()],reps3+1,"1"]).draw();
				                    //lista.add(meses[dt.getMonth()] + cont3+1 + " Nivel " +"2");
				                    planets[ind]= meses[dt.getMonth()] +" "+ cont3+1 + " Nivel " +"3"    ;
				        			mt=m;
				        			at=a;
				        			cont3=0;
				        			ind++;
				        			}

				        			else {


				        			mt=0;
				        			at=0;






				        			}
				        		
				        		
				        		
				        		
				        		
				        		
				        	}
				        	
				         
				        	
				        	
				        	
				        	
				        	
				            
				        } else {
				      
				        }
				    }

					
				});
				
			}
			});
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
