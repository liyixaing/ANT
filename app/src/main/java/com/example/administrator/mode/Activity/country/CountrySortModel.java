package com.example.administrator.mode.Activity.country;

/**
 * Created by admin on 2016/7/22.
 */


/**
 * 类简要描述
 * <p/>
 * <p>
 * 类详细描述
 * </p>
 *
 * @author duanbokan
 */

public class CountrySortModel extends CountryModel

{
    // 显示数据拼音的首字母
    public String sortLetters;
    public String image;
    public String id;  //国家id
    public CountrySortToken sortToken = new CountrySortToken();
    public CountrySortModel(String name, String number, String countrySortKey) {
        super(name, number, countrySortKey);
    }

}
