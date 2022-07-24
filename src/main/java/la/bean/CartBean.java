package la.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * カートを管理するJavaBean
 * @author tutor
 */
public class CartBean {

	/**
	 * クラスフィールド
	 */
	private List<ItemBean> items;	// カート内商品リスト
	private int totalPrice;					// カート内相品の総額
	
	/**
	 * コンストラクタ
	 */
	public CartBean() {
		this.items = new ArrayList<>();
	}

	/**
	 * カート内商品リストを取得する。
	 * @return items カート内商品リスト
	 */
	public List<ItemBean> getItems() {
		return items;
	}

	/**
	 * カート内商品の総額を取得する。
	 * @return totalPrice カート内商品の総額
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * カートに商品を追加する。
	 * @param item 追加するItemBeanのインスタンス
	 * @param quantity 追加する数量
	 */
	public void addCart(ItemBean item, int quantity) {
		// 追加する商品がカート内にある場合の処理
		for (ItemBean bean : this.items) {
			if (bean.getCode() == item.getCode()) {
				// 追加する商品と同じ商品番号の商品が見つかった場合
				// 商品の個数を再計算：カートに入っている商品の個数に新しい個数を追加
				int newQuantity = bean.getQuantity() + quantity;
				// 新しい商品の個数を再設定：参照変数なので再設定するだけ
				bean.setQuantity(newQuantity);
				// カート内リストの処品の総額を再計算
				this.recalcTotalPrice();
				return; // 処理を強制終了
			}
		}
		
		// カート内に追加する商品が見つからない場合
		item.setQuantity(quantity);
		this.items.add(item);
		// カート内リストの処品の総額を再計算
		this.recalcTotalPrice();
	}
	
	/**
	 * カート内商品リストの総額を再計算する。
	 */
	private void recalcTotalPrice() {
		int totalPrice = 0;
		for (ItemBean item : this.items) {
			totalPrice += item.getPrice() * item.getQuantity();
		}
		this.totalPrice = totalPrice;
	}
}
