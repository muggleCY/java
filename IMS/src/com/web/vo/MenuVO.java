package com.web.vo;

/**
 * @Author zyb
 * @TIME 19-12-13
 **/
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class MenuVO {
    private Integer id;
    private String hrefUrl;
    private Integer parentId;
    private String menuName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
