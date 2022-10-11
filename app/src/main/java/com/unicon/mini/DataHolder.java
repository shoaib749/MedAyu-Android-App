package com.unicon.mini;

public class DataHolder {
    String BName,AName,consume,use,side,part,Category,region,condition,constraint,vitamins;
    String treeURL, leafURL;

    public DataHolder(String BName, String AName, String consume, String use, String side, String part, String category, String region, String condition, String constraint, String vitamins, String treeURL, String leafURL) {
        this.BName = BName;
        this.AName = AName;
        this.consume = consume;
        this.use = use;
        this.side = side;
        this.part = part;
        Category = category;
        this.region = region;
        this.condition = condition;
        this.constraint = constraint;
        this.vitamins = vitamins;
        this.treeURL = treeURL;
        this.leafURL = leafURL;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getVitamins() {
        return vitamins;
    }

    public void setVitamins(String vitamins) {
        this.vitamins = vitamins;
    }

    public String getTreeURL() {
        return treeURL;
    }

    public void setTreeURL(String treeURL) {
        this.treeURL = treeURL;
    }

    public String getLeafURL() {
        return leafURL;
    }

    public void setLeafURL(String leafURL) {
        this.leafURL = leafURL;
    }
}
