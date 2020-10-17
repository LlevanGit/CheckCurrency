package com.example.checkcurrency.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkcurrency.Adapters.MainRecyclerAdapter;
import com.example.checkcurrency.R;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class FragmentCurrencies extends Fragment {

    public String sstring;
    public int lenn;
    public RecyclerView mrecyclerView;
    public MainRecyclerAdapter adapter;
    ArrayList<String> currency_names = new ArrayList<>();
    ArrayList<String> currency_symbols = new ArrayList<>();
    ArrayList<String> currency_rates = new ArrayList<>();
    ArrayList<String> currency_changes = new ArrayList<>();
    ArrayList<Integer> currency_GIF_ = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.currencies_fragment_layout, viewGroup, false);
        mrecyclerView = view.findViewById(R.id.recycler_currencies);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        rssAsyncTask asyncTask = new rssAsyncTask();
        asyncTask.execute();


        return view;

    }

    public  class rssAsyncTask extends AsyncTask<Void,Integer,String> {
        String resp;

        @Override
        protected String doInBackground(Void... voids) {
            try {
                parseRSS();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            adapter=new MainRecyclerAdapter(getActivity(),currency_names,currency_symbols,currency_rates,currency_changes,currency_GIF_);
            mrecyclerView.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        public void parseRSS() throws XmlPullParserException {
            try {

                URL url = new URL("http://www.nbg.ge/rss.php");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName("item");
                Node node = nodeList.item(0);
                Element firstEl = (Element) node;


                NodeList websiteList = firstEl.getElementsByTagName("description");
                Element websiteElement = (Element) websiteList.item(0);
                NodeList currInfo = websiteElement.getChildNodes();

                sstring =currInfo.item(0).getNodeValue();
                org.jsoup.nodes.Document currInfoHtml = Jsoup.parse(sstring);
                Elements rows=currInfoHtml.select("tr");
                for (org.jsoup.nodes.Element row:rows){
                    Elements oneRow=row.select("td");
                    sstring =oneRow.toString();
                    sstring =oneRow.get(3).select("img").attr("src");
                    currency_symbols.add(oneRow.get(0).ownText());
                    currency_names.add(oneRow.get(1).ownText());
                    currency_rates.add(oneRow.get(2).ownText());
                    lenn=oneRow.get(3).select("img").attr("src").length();
                    sstring =oneRow.get(3).select("img").attr("src").substring(lenn-7,lenn-4);
                    if(oneRow.get(3).select("img").attr("src").substring(lenn-7,lenn-4).equals("red")){
                        currency_GIF_.add(0);
                    }else{
                        currency_GIF_.add(1);
                    }
                    currency_changes.add(oneRow.get(4).ownText());
                }


            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ParserConfigurationException parserConfigurationException) {
                parserConfigurationException.printStackTrace();
            } catch (SAXException saxException) {
                saxException.printStackTrace();
            }
        }

    }






}

