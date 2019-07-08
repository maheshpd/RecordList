package com.example.mayank.recordlist.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mayank.recordlist.ItemModel;
import com.example.mayank.recordlist.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.provider.Settings.System.DATE_FORMAT;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyItemViewHolder> {

    ArrayList<ItemModel> list;
    Context context;

    public ItemAdapter(ArrayList<ItemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyItemViewHolder(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int i) {
        ItemModel im = list.get(i);
        holder.FundedVal.setText(im.getCollectedValue());
        holder.goalsVal.setText(im.getTotalValue());
        holder.title.setText(im.getTitle());
        holder.shortDes.setText(im.getShortDescription());

        Picasso.get().load(im.getMainImageURL()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.ima);

        String startdate = im.getStartDate();
        String endDate = im.getEndDate();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
//        LocalDate start = LocalDate.parse(startdate,formatter);
//        LocalDate end = LocalDate.parse(endDate,formatter);
//
//        System.out.println(ChronoUnit.DAYS.between(start, end));



        try {
            //Dates to compare
//            String CurrentDate=  "09/24/2015";
//            String FinalDate=  "09/26/2015";

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

            //Setting dates
            date1 = dates.parse(endDate);
            date2 = dates.parse(startdate);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (1000 * 60 * 60 * 24);

            //Convert long to String
            String dayDifference = Long.toString(differenceDates);


            holder.totaldate.setText(dayDifference);
        } catch (Exception exception) {
        }



//        holder.totaldate.setText(getCountOfDays(startdate,endDate));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public String getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        }


    /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return ("" + (int) dayCount + " Days");
    }



//    public static long getDaysBetweenDates(String start, String end) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
//        Date startDate, endDate;
//        long numberOfDays = 0;
//        try {
//            startDate = dateFormat.parse(start);
//            endDate = dateFormat.parse(end);
//            numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return numberOfDays;
//    }
//
//    private static long getUnitBetweenDates(Date startDate, Date endDate, TimeUnit unit) {
//        long timeDiff = endDate.getTime() - startDate.getTime();
//        return unit.convert(timeDiff, TimeUnit.MILLISECONDS);
//    }
    class MyItemViewHolder extends RecyclerView.ViewHolder {

        ImageView ima;
        TextView FundedVal, goalsVal, endVal, title, shortDes,totaldate;

        public MyItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ima = itemView.findViewById(R.id.imageView);
            FundedVal = itemView.findViewById(R.id.rupes);
            goalsVal = itemView.findViewById(R.id.rupes1);
            endVal = itemView.findViewById(R.id.number);
            title = itemView.findViewById(R.id.title);
            shortDes = itemView.findViewById(R.id.shortDescription);
            totaldate = itemView.findViewById(R.id.number);
        }
    }
}
