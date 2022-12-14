package com.nsu_map;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;


/**** 네이버 객체 api 가져오기 ****/
public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback {


    /**** 내 위치 오버레이 ****/
    private static final int LOCATION_PERMiSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;


    /**검색 **/
    private List<String> list;

    /**** 메인 버튼 이동 버튼 ****/
    Button mbtn_url;


    Button sebt;

    EditText editText;


    @SuppressLint("CutPasteId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationSource =
                new FusedLocationSource(this, LOCATION_PERMiSION_REQUEST_CODE);


        // 리스트 생성
        list = new ArrayList<String>();

        //리스트에 검색될 데이터 입력
        settingList();

        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)
        findViewById(R.id.ac_text_view);

        // 어뎁타 데이터 연결
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,list));

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });




        /** 지도 객체 생성 **/
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        // 검색 확인 버튼
        editText = findViewById(R.id.ac_text_view);
        sebt = findViewById(R.id.searchbt);
        sebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals("학복관")) {
                    showwelfare();
                }
                else if (editText.getText().toString().equals("학생복지회관")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("식당-학생복지회관")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("카페-학생복지회관")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("도서관")){
                    showlibary();
                }
                else if (editText.getText().toString().equals("버스 정류장")){
                    showDialog();
                }
                else if (editText.getText().toString().equals("공학관")){
                    showengineering1();
                }
                else if (editText.getText().toString().equals("채움")){
                    showfill();
                }
                else if (editText.getText().toString().equals("프린터")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("편의점")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("카페-도서관")){
                    showlibary();
                }
                else if (editText.getText().toString().equals("atm")){
                    showengineering1();
                }
                else if (editText.getText().toString().equals("식당-채움")){
                    showfill();
                }
                else if (editText.getText().toString().equals("우체국")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("브레댄코")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("문구점")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("서점")){
                    showwelfare();
                }
                else if (editText.getText().toString().equals("열람실")){
                    showlibary();
                }
                else if (editText.getText().toString().equals("도서 대출실")){
                    showlibary();
                }
                else if (editText.getText().toString().equals("자료실")){
                    showlibary();
                }
                else if (editText.getText().toString().equals("김점구 교수님")){
                    showprofessor01();
                }
                else if (editText.getText().toString().equals("송은지 교수님")){
                    showprofessor02();
                }
                else if (editText.getText().toString().equals("황정희 교수님")){
                    showprofessor03();
                }
                else if (editText.getText().toString().equals("김현철 교수님")){
                    showprofessor04();
                }
                else if (editText.getText().toString().equals("나상엽 교수님")){
                    showprofessor05();
                }
                else if (editText.getText().toString().equals("정지문 교수님")){
                    showprofessor06();
                }
                else if (editText.getText().toString().equals("김정길 교수님")){
                    showprofessor07();
                }
                else if (editText.getText().toString().equals("사무실-컴소과")){
                    showengineering1();
                }
                else if (editText.getText().toString().equals("학생회실")){
                    showengineering1();
                }

            }
        });


        /** 메인 메뉴 홈페이지 이동 버튼 **/
        mbtn_url = findViewById((R.id.btn_url));
        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R5kK6FmuHak"));
                startActivity(urlintent);
            }
        });


        /** 메인 메뉴 홈페이지 이동 버튼 **/
        mbtn_url = findViewById((R.id.btn_url2));
        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R5kK6FmuHak"));
                startActivity(urlintent);
            }
        });




    }

    /**** 버스 시간표 시트 애니메이션 ****/
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bus_sheet);


        Button bus = dialog.findViewById(R.id.mbusbtn);
        Button bus2 = dialog.findViewById(R.id.bus_btn2);

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=59%25"));
                startActivity(urlintent);
            }
        });

        bus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=59%25"));
                startActivity(urlintent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }

    /** 채움 식당 시트 애니메이션 **/
    private void showfill() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fill_sheet);


        Button fill = dialog.findViewById(R.id.fill_btn);
        Button fill2 = dialog.findViewById(R.id.fill_btn2);
        Button fill3 = dialog.findViewById(R.id.li1);
        Button fill4 = dialog.findViewById(R.id.li2);
        Button fill5 = dialog.findViewById(R.id.li3);


        /** 버튼 스크롤 이동 **/
        ScrollView fiscrol = dialog.findViewById(R.id.fima);


        /**채움 유튜브 이동 버튼 **/
        fill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=485%25"));
                startActivity(urlintent);
            }
        });

        fill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=485%25"));
                startActivity(urlintent);

            }
        });

        /** 음식 이동 버튼 **/
        fill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiscrol.smoothScrollTo(0,1180);

            }
        });

        /** 음료 이동 버튼 **/
        fill4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiscrol.smoothScrollTo(0,1800);

            }
        });

        /** 화장실 이동 버튼 **/
        fill5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fiscrol.smoothScrollTo(0,1800);

            }
        });



        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }

    /** 성암 도서실 애니메이션 **/

    private void showlibary() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.library_sheet);


        Button libraryb = dialog.findViewById(R.id.library_btn);


        /** 열람실 스크롤 이동 **/
        ScrollView libraryscrol = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn = dialog.findViewById(R.id.bookscroll1);

        /** 자판기 스크롤 이동 **/
        ScrollView libraryscrol2 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn2 = dialog.findViewById(R.id.bookscroll2);

        /** 화장실 스크롤 이동 **/
        ScrollView libraryscrol3 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn3 = dialog.findViewById(R.id.bookscroll3);

        /** 카페 스크롤 이동 **/
        ScrollView libraryscrol4 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn4 = dialog.findViewById(R.id.bookscroll4);

        /** 도서 대출실 스크롤 이동 **/
        ScrollView libraryscrol5 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn5 = dialog.findViewById(R.id.bookscroll5);

        /** 자료실 스크롤 이동 **/
        ScrollView libraryscrol6 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn6 = dialog.findViewById(R.id.bookscroll6);

        /** 자료실 스크롤 이동 **/
        ScrollView libraryscrol7 = dialog.findViewById(R.id.libraryscrol);
        Button bookbtn7 = dialog.findViewById(R.id.bookscroll7);




        bookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol.smoothScrollTo(0,1270);

            }
        });

        bookbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol2.smoothScrollTo(0,2800);

            }
        });

        bookbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol3.smoothScrollTo(0,3700);

            }
        });

        bookbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol4.smoothScrollTo(0,4500);

            }
        });

        bookbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol5.smoothScrollTo(0,5900);

            }
        });

        bookbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol6.smoothScrollTo(0,7400);

            }
        });


        bookbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libraryscrol7.smoothScrollTo(0,7900);

            }
        });



        libraryb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=FbBCbuwcNIM&t=232s"));
                startActivity(urlintent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /** 학복관 애니메이션 **/

    private void showwelfare() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.welfare_sheet);


        Button welb = dialog.findViewById(R.id.welfare_btn);

        /** 1층식당 스크롤 이동 **/
        ScrollView welscrol = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn = dialog.findViewById(R.id.walfare_scroll_btn01);

        /** 2층식당 스크롤 이동 **/
        ScrollView welscrol02 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn02 = dialog.findViewById(R.id.walfare_scroll_btn02);

        /** 편의점 스크롤 이동 **/
        ScrollView welscrol03 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn03 = dialog.findViewById(R.id.walfare_scroll_btn03);

        /** 카페 스크롤 이동 **/
        ScrollView welscrol04 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn04 = dialog.findViewById(R.id.walfare_scroll_btn04);

        /** 프린터실 스크롤 이동 **/
        ScrollView welscrol05 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn05 = dialog.findViewById(R.id.walfare_scroll_btn05);

        /** 서점 스크롤 이동 **/
        ScrollView welscrol06 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn06 = dialog.findViewById(R.id.walfare_scroll_btn06);

        /** 문구점 스크롤 이동 **/
        ScrollView welscrol07 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn07 = dialog.findViewById(R.id.walfare_scroll_btn07);

        /** 우체국 스크롤 이동 **/
        ScrollView welscrol08 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn08 = dialog.findViewById(R.id.walfare_scroll_btn08);

        /** 브레댄코 스크롤 이동 **/
        ScrollView welscrol09 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn09 = dialog.findViewById(R.id.walfare_scroll_btn09);

        /** 화장실 스크롤 이동 **/
        ScrollView welscrol10 = dialog.findViewById(R.id.welfare_scrol);
        Button welfbtn10 = dialog.findViewById(R.id.walfare_scroll_btn10);




        welfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol.smoothScrollTo(0,1250);

            }
        });

        welfbtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol02.smoothScrollTo(0,2000);

            }
        });

        welfbtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol03.smoothScrollTo(0,2800);

            }
        });

        welfbtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol04.smoothScrollTo(0,3650);

            }
        });

        welfbtn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol05.smoothScrollTo(0,4500);

            }
        });

        welfbtn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol06.smoothScrollTo(0,5350);

            }
        });

        welfbtn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol07.smoothScrollTo(0,6200);

            }
        });

        welfbtn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol08.smoothScrollTo(0,7050);

            }
        });

        welfbtn09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol09.smoothScrollTo(0,7900);

            }
        });

        welfbtn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welscrol10.smoothScrollTo(0,8500);

            }
        });

        welb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R5kK6FmuHak"));
                startActivity(urlintent);
            }
        });




        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 공학1관 시트 애니메이션 ****/
    private void showengineering1() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.engineering1_sheet);


        /** 교수님 연구실 스크롤 이동 **/
        ScrollView rhdgkrrhks01 = dialog.findViewById(R.id.eng_scroll);
        Button rhdgkrrhksbtn01 = dialog.findViewById(R.id.engin1_2);

        /** 컴소과 사무실 스크롤 이동 **/
        ScrollView rhdgkrrhks02 = dialog.findViewById(R.id.eng_scroll);
        Button rhdgkrrhksbtn02 = dialog.findViewById(R.id.engin1_3);

        /** 학생회실 스크롤 이동 **/
        ScrollView rhdgkrrhks03 = dialog.findViewById(R.id.eng_scroll);
        Button rhdgkrrhksbtn03 = dialog.findViewById(R.id.engin1_4);

        /** ATM 스크롤 이동 **/
        ScrollView rhdgkrrhks04 = dialog.findViewById(R.id.eng_scroll);
        Button rhdgkrrhksbtn04 = dialog.findViewById(R.id.engin1_5);

        rhdgkrrhksbtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rhdgkrrhks01.smoothScrollTo(0,1100);

            }
        });

        rhdgkrrhksbtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rhdgkrrhks02.smoothScrollTo(0,1400);

            }
        });

        rhdgkrrhksbtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rhdgkrrhks03.smoothScrollTo(0,2200);

            }
        });

        rhdgkrrhksbtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rhdgkrrhks04.smoothScrollTo(0,2600);

            }
        });


        /**** 김점구 교수님 정보 ****/
        Button professor01 = dialog.findViewById(R.id.rlawjarn);
        /**** 송은지 교수님 정보 ****/
        Button professor02 = dialog.findViewById(R.id.thddmswl);
        /**** 황정희 교수님 정보 ****/
        Button professor03 = dialog.findViewById(R.id.ghkdwjdgml);
        /**** 김현철 교수님 정보 ****/
        Button professor04 = dialog.findViewById(R.id.rlaguscjf);
        /**** 나상엽 교수님 정보 ****/
        Button professor05 = dialog.findViewById(R.id.sktkdduq);
        /**** 정지문 교수님 정보 ****/
        Button professor06 = dialog.findViewById(R.id.wjdwlans);
        /**** 김정길 교수님 정보 ****/
        Button professor07 = dialog.findViewById(R.id.rlawjdrlf);


        /**** 김점구 교수님 정보 불러오기 ****/
        professor01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor01();
            }
        });

        /**** 송은지 교수님 정보 불러오기 ****/
        professor02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor02();
            }
        });

        /**** 황정희 교수님 정보 불러오기 ****/
        professor03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor03();
            }
        });

        /**** 김현철 교수님 정보 불러오기 ****/
        professor04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor04();
            }
        });


        /**** 나상엽 교수님 정보 불러오기 ****/
        professor05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor05();
            }
        });


        /**** 정지문 교수님 정보 불러오기 ****/
        professor06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor07();
            }
        });


        /**** 김정길 교수님 정보 불러오기 ****/
        professor07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                showprofessor07();
            }
        });





        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 김점구 교수님 정보 시트 애니메이션 ****/
    private void showprofessor01() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor01);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 송은지 교수님 정보 시트 애니메이션 ****/
    private void showprofessor02() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor02);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 황정희 교수님 정보 시트 애니메이션 ****/
    private void showprofessor03() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor03);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }

    /**** 김현철 교수님 정보 시트 애니메이션 ****/
    private void showprofessor04() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor04);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }

    /**** 나상엽 교수님 정보 시트 애니메이션 ****/
    private void showprofessor05() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor05);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 정지문 교수님 정보 시트 애니메이션 ****/
    private void showprofessor06() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor06);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    /**** 김정길 교수님 정보 시트 애니메이션 ****/
    private void showprofessor07() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.professor07);


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }


    public void settingList(){
        list.add("학복관");
        list.add("학생복지회관");
        list.add("식당-학생복지회관");
        list.add("카페-학생복지회관");
        list.add("도서관");
        list.add("버스 정류장");
        list.add("공학관");
        list.add("채움");
        list.add("프린터");
        list.add("편의점");
        list.add("카페-도서관");
        list.add("atm");
        list.add("식당-채움");
        list.add("우체국");
        list.add("브레덴코");
        list.add("문구점");
        list.add("서점");
        list.add("열람실");
        list.add("도서 대출실");
        list.add("자료실");
        list.add("김점구 교수님");
        list.add("송은지 교수님");
        list.add("황정희 교수님");
        list.add("김현철 교수님");
        list.add("나상엽 교수님");
        list.add("정지문 교수님");
        list.add("김정길 교수님");
        list.add("사무실-컴소과");
        list.add("학생회실");

    }





    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        /**** 내 위치 오버레이 ****/
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);

        /****버스정류장 마커 ****/
        Marker marker = new Marker();
        marker.setPosition(new LatLng(36.9104, 127.1422));
        marker.setMap(naverMap);
        marker.setCaptionText("셔틀 버스 정류장");
        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showDialog();
                return false;
            }
        });

        /**** 채움 마커 ****/
        Marker marker2 = new Marker();
        marker2.setCaptionText("채움");
        marker2.setPosition(new LatLng(36.908934, 127.146289));
        marker2.setMap(naverMap);
        marker2.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showfill();
                return false;
            }
        });

        /**** 성암 도서실 마커 *****/
        Marker marker3 = new Marker();
        marker3.setCaptionText("성암 도서관");
        marker3.setPosition(new LatLng(36.909000, 127.143511));
        marker3.setMap(naverMap);
        marker3.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showlibary();
                return false;
            }
        });


        /**** 학복관 마커 *****/
        Marker marker4 = new Marker();
        marker4.setCaptionText("학생 복지회관(학복관)");
        marker4.setPosition(new LatLng(36.909793, 127.143565));
        marker4.setMap(naverMap);
        marker4.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showwelfare();
                return false;
            }
        });

        /**** 공학관 *****/
        Marker marker5 = new Marker();
        marker5.setCaptionText("공학관");
        marker5.setPosition(new LatLng(36.907301, 127.143043));
        marker5.setMap(naverMap);
        marker5.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showengineering1();
                return false;
            }
        });



        /**** 네이버 지도 UI 설정 ****/
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);      // 줌 활성화
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setCompassEnabled(true);       // 네이버 줌 버튼 활성화


    }



}



