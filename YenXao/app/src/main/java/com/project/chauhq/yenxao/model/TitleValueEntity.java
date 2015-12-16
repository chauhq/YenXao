package com.project.chauhq.yenxao.model;

/**
 * @author ChauHQ
 */
public class TitleValueEntity {

    /**
     * <p>
     * Title
     * </p>
     * <p>
     * タイトル
     * </p>
     * <p>
     * 标题
     * </p>
     */
    private String title;

    /**
     * <p>
     * Value
     * </p>
     * <p>
     * 値
     * </p>
     * <p>
     * 值
     * </p>
     */
    private float value;

    /**
     *
     * <p>
     * Constructor of TitleValueEntity
     * </p>
     * <p>
     * TitleValueEntity类对象的构造函数
     * </p>
     * <p>
     * TitleValueEntityのコンストラクター
     * </p>
     *
     * @param title
     *            <p>
     *            Title
     *            </p>
     *            <p>
     *            タイトル
     *            </p>
     *            <p>
     *            标题
     *            </p>
     * @param value
     *            <p>
     *            Value
     *            </p>
     *            <p>
     *            値
     *            </p>
     *            <p>
     *            值
     *            </p>
     */
    public TitleValueEntity(String title, float value) {
        super();
        this.title = title;
        this.value = value;
    }

    /**
     *
     * <p>
     * Constructor of TitleValueEntity
     * </p>
     * <p>
     * TitleValueEntity类对象的构造函数
     * </p>
     * <p>
     * TitleValueEntityのコンストラクター
     * </p>
     *
     */
    public TitleValueEntity() {
        super();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value
     */
    public float getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(float value) {
        this.value = value;
    }

}