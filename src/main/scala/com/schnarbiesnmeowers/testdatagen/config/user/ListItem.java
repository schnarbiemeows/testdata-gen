package com.schnarbiesnmeowers.testdatagen.config.user;

import java.math.BigDecimal;

public class ListItem {
    private int customerid;
    private String dataconfigname;
    private int order;
    private int dependency;
    private String dataname;
    private String datatype;
    private boolean canbenull;
    private boolean canbeblank;
    private boolean canbeinvalid;
    private int nullpercent;
    private int blankpercent;
    private int invalidpercent;
    private boolean isfixedlength;
    private boolean usedistinctvals;
    private int distinctvalues;
    private int fixedlength;
    private int minlength;
    private int maxlength;
    private boolean allowuppers;
    private boolean allowlowers;
    private boolean allownumbers;
    private boolean allowspecials;
    private String inclusions;
    private String exclusions;
    private boolean usepattern;
    private String pattern;
    private String minvaluestr;
    private String maxvaluestr;
    private long minvalwhole;
    private long maxvalwhole;
    private boolean isranged;
    private String basevaluestr;
    private String incrementstr;
    private long basevalue;
    private long increment;
    private double minvaldecimal;
    private double maxvaldecimal;
    private double basevaldecimal;
    private double incrementdecimal;
    private int signdigits;
    private boolean hasdate;
    private boolean hastime;
    private String format;
    private String startdatetime;
    private String enddatetime;
    private String basedatetime;
    private String basetimetime;
    private int inc_yr_str;
    private int inc_mth_str;
    private int inc_day_str;
    private int inc_hrs_str;
    private int inc_min_str;
    private int inc_sec_str;

    public ListItem() {}
    public ListItem(int customerid, String dataconfigname, int order, int dependency, String dataname, String datatype, boolean canbenull,
                    boolean canbeblank, boolean canbeinvalid, int nullpercent, int blankpercent, int invalidpercent,
                    boolean isfixedlength, boolean usedistinctvals, int distinctvalues, int fixedlength, int minlength,
                    int maxlength, boolean allowuppers, boolean allowlowers, boolean allownumbers, boolean allowspecials,
                    String inclusions, String exclusions, boolean usepattern, String pattern, String minvaluestr,
                    String maxvaluestr, long minvalwhole, long maxvalwhole, boolean isranged, String basevaluestr,
                    String incrementstr, long basevalue, long increment, double minvaldecimal, double maxvaldecimal,
                    double basevaldecimal, double incrementdecimal, int signdigits, boolean hasdate, boolean hastime,
                    String format, String startdatetime, String enddatetime, String basedatetime, String basetimetime,
                    int inc_yr_str, int inc_mth_str, int inc_day_str, int inc_hrs_str, int inc_min_str, int inc_sec_str) {
        this.customerid = customerid;
        this.dataconfigname = dataconfigname;
        this.order = order;
        this.dependency = dependency;
        this.dataname = dataname;
        this.datatype = datatype;
        this.canbenull = canbenull;
        this.canbeblank = canbeblank;
        this.canbeinvalid = canbeinvalid;
        this.nullpercent = nullpercent;
        this.blankpercent = blankpercent;
        this.invalidpercent = invalidpercent;
        this.isfixedlength = isfixedlength;
        this.usedistinctvals = usedistinctvals;
        this.distinctvalues = distinctvalues;
        this.fixedlength = fixedlength;
        this.minlength = minlength;
        this.maxlength = maxlength;
        this.allowuppers = allowuppers;
        this.allowlowers = allowlowers;
        this.allownumbers = allownumbers;
        this.allowspecials = allowspecials;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
        this.usepattern = usepattern;
        this.pattern = pattern;
        this.minvaluestr = minvaluestr;
        this.maxvaluestr = maxvaluestr;
        this.minvalwhole = minvalwhole;
        this.maxvalwhole = maxvalwhole;
        this.isranged = isranged;
        this.basevaluestr = basevaluestr;
        this.incrementstr = incrementstr;
        this.basevalue = basevalue;
        this.increment = increment;
        this.minvaldecimal = minvaldecimal;
        this.maxvaldecimal = maxvaldecimal;
        this.basevaldecimal = basevaldecimal;
        this.incrementdecimal = incrementdecimal;
        this.signdigits = signdigits;
        this.hasdate = hasdate;
        this.hastime = hastime;
        this.format = format;
        this.startdatetime = startdatetime;
        this.enddatetime = enddatetime;
        this.basedatetime = basedatetime;
        this.basetimetime = basetimetime;
        this.inc_yr_str = inc_yr_str;
        this.inc_mth_str = inc_mth_str;
        this.inc_day_str = inc_day_str;
        this.inc_hrs_str = inc_hrs_str;
        this.inc_min_str = inc_min_str;
        this.inc_sec_str = inc_sec_str;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getDataconfigname() {
        return dataconfigname;
    }

    public void setDataconfigname(String dataconfigname) {
        this.dataconfigname = dataconfigname;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public boolean isCanbenull() {
        return canbenull;
    }

    public void setCanbenull(boolean canbenull) {
        this.canbenull = canbenull;
    }

    public boolean isCanbeblank() {
        return canbeblank;
    }

    public void setCanbeblank(boolean canbeblank) {
        this.canbeblank = canbeblank;
    }

    public boolean isCanbeinvalid() {
        return canbeinvalid;
    }

    public void setCanbeinvalid(boolean canbeinvalid) {
        this.canbeinvalid = canbeinvalid;
    }

    public int getNullpercent() {
        return nullpercent;
    }

    public void setNullpercent(int nullpercent) {
        this.nullpercent = nullpercent;
    }

    public int getBlankpercent() {
        return blankpercent;
    }

    public void setBlankpercent(int blankpercent) {
        this.blankpercent = blankpercent;
    }

    public int getInvalidpercent() {
        return invalidpercent;
    }

    public void setInvalidpercent(int invalidpercent) {
        this.invalidpercent = invalidpercent;
    }

    public boolean isIsfixedlength() {
        return isfixedlength;
    }

    public void setIsfixedlength(boolean isfixedlength) {
        this.isfixedlength = isfixedlength;
    }

    public boolean isUsedistinctvals() {
        return usedistinctvals;
    }

    public void setUsedistinctvals(boolean usedistinctvals) {
        this.usedistinctvals = usedistinctvals;
    }

    public int getDistinctvalues() {
        return distinctvalues;
    }

    public void setDistinctvalues(int distinctvalues) {
        this.distinctvalues = distinctvalues;
    }

    public int getFixedlength() {
        return fixedlength;
    }

    public void setFixedlength(int fixedlength) {
        this.fixedlength = fixedlength;
    }

    public int getMinlength() {
        return minlength;
    }

    public void setMinlength(int minlength) {
        this.minlength = minlength;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    public boolean isAllowuppers() {
        return allowuppers;
    }

    public void setAllowuppers(boolean allowuppers) {
        this.allowuppers = allowuppers;
    }

    public boolean isAllowlowers() {
        return allowlowers;
    }

    public void setAllowlowers(boolean allowlowers) {
        this.allowlowers = allowlowers;
    }

    public boolean isAllownumbers() {
        return allownumbers;
    }

    public void setAllownumbers(boolean allownumbers) {
        this.allownumbers = allownumbers;
    }

    public boolean isAllowspecials() {
        return allowspecials;
    }

    public void setAllowspecials(boolean allowspecials) {
        this.allowspecials = allowspecials;
    }

    public String getInclusions() {
        return inclusions;
    }

    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }

    public boolean isUsepattern() {
        return usepattern;
    }

    public void setUsepattern(boolean usepattern) {
        this.usepattern = usepattern;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMinvaluestr() {
        return minvaluestr;
    }

    public void setMinvaluestr(String minvaluestr) {
        this.minvaluestr = minvaluestr;
    }

    public String getMaxvaluestr() {
        return maxvaluestr;
    }

    public void setMaxvaluestr(String maxvaluestr) {
        this.maxvaluestr = maxvaluestr;
    }

    public long getMinvalwhole() {
        return minvalwhole;
    }

    public void setMinvalwhole(long minvalwhole) {
        this.minvalwhole = minvalwhole;
    }

    public long getMaxvalwhole() {
        return maxvalwhole;
    }

    public void setMaxvalwhole(long maxvalwhole) {
        this.maxvalwhole = maxvalwhole;
    }

    public boolean isIsranged() {
        return isranged;
    }

    public void setIsranged(boolean isranged) {
        this.isranged = isranged;
    }

    public String getBasevaluestr() {
        return basevaluestr;
    }

    public void setBasevaluestr(String basevaluestr) {
        this.basevaluestr = basevaluestr;
    }

    public String getIncrementstr() {
        return incrementstr;
    }

    public void setIncrementstr(String incrementstr) {
        this.incrementstr = incrementstr;
    }

    public long getBasevalue() {
        return basevalue;
    }

    public void setBasevalue(long basevalue) {
        this.basevalue = basevalue;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }

    public double getMinvaldecimal() {
        return minvaldecimal;
    }

    public void setMinvaldecimal(double minvaldecimal) {
        this.minvaldecimal = minvaldecimal;
    }

    public double getMaxvaldecimal() {
        return maxvaldecimal;
    }

    public void setMaxvaldecimal(double maxvaldecimal) {
        this.maxvaldecimal = maxvaldecimal;
    }

    public double getBasevaldecimal() {
        return basevaldecimal;
    }

    public void setBasevaldecimal(double basevaldecimal) {
        this.basevaldecimal = basevaldecimal;
    }

    public double getIncrementdecimal() {
        return incrementdecimal;
    }

    public void setIncrementdecimal(double incrementdecimal) {
        this.incrementdecimal = incrementdecimal;
    }

    public int getSigndigits() {
        return signdigits;
    }

    public void setSigndigits(int signdigits) {
        this.signdigits = signdigits;
    }

    public boolean isHasdate() {
        return hasdate;
    }

    public void setHasdate(boolean hasdate) {
        this.hasdate = hasdate;
    }

    public boolean isHastime() {
        return hastime;
    }

    public void setHastime(boolean hastime) {
        this.hastime = hastime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStartdatetime() {
        return startdatetime;
    }

    public void setStartdatetime(String startdatetime) {
        this.startdatetime = startdatetime;
    }

    public String getEnddatetime() {
        return enddatetime;
    }

    public void setEnddatetime(String enddatetime) {
        this.enddatetime = enddatetime;
    }

    public String getBasedatetime() {
        return basedatetime;
    }

    public void setBasedatetime(String basedatetime) {
        this.basedatetime = basedatetime;
    }

    public String getBasetimetime() {
        return basetimetime;
    }

    public void setBasetimetime(String basetimetime) {
        this.basetimetime = basetimetime;
    }

    public int getInc_yr_str() {
        return inc_yr_str;
    }

    public void setInc_yr_str(int inc_yr_str) {
        this.inc_yr_str = inc_yr_str;
    }

    public int getInc_mth_str() {
        return inc_mth_str;
    }

    public void setInc_mth_str(int inc_mth_str) {
        this.inc_mth_str = inc_mth_str;
    }

    public int getInc_day_str() {
        return inc_day_str;
    }

    public void setInc_day_str(int inc_day_str) {
        this.inc_day_str = inc_day_str;
    }

    public int getInc_hrs_str() {
        return inc_hrs_str;
    }

    public void setInc_hrs_str(int inc_hrs_str) {
        this.inc_hrs_str = inc_hrs_str;
    }

    public int getInc_min_str() {
        return inc_min_str;
    }

    public void setInc_min_str(int inc_min_str) {
        this.inc_min_str = inc_min_str;
    }

    public int getInc_sec_str() {
        return inc_sec_str;
    }

    public void setInc_sec_str(int inc_sec_str) {
        this.inc_sec_str = inc_sec_str;
    }
}
