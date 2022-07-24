package la.bean;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class CartBeanTest {
	
	/** テスト対象クラス：syste under test */
	private CartBean sut;
	
	@BeforeEach
	void setUp() throws Exception {
		this.sut = new CartBean();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("CartBEan#getTotalPriceメソッドのテストクラス")
	class GetTotalPriceTest {
		@Test
		@DisplayName("商品番号「７」の商品１個入っている状態で商品番号「７」の商品を３個追加すると商品の総額は3120円である")
		void test_23() {
			// setup
			ItemBean target = new ItemBean(7, "パズルゲーム", 780);
			int quantity = 3;
			int expected = 3120;
			// execute
			sut.addCart(new ItemBean(7, "パズルゲーム", 780), 1);
			sut.addCart(target, quantity);
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		
		@Test
		@DisplayName("商品番号「７」の商品が１個が入っている状態で商品番号「２」の商品を１個追加すると商品の総額は1760円である")
		void test_22() {
			// setup
			ItemBean target = new ItemBean(2, "MLB Fun", 980);
			int quantity = 1;
			int expected = 1760; 
			// execute
			sut.addCart(new ItemBean(7, "パズルゲーム", 780), 1);
			sut.addCart(target, quantity);
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		
		@Test
		@DisplayName("空のカートに商品番号「７」の商品を１個追加すると商品の総額は780円である")
		void test_21() {
			// setup
			ItemBean target = new ItemBean(7, "パズルゲーム", 780);
			int quantity = 1;
			int expected = 780;
			// execute
			sut.addCart(target, quantity);
			int actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
	}
	
	@Nested
	@DisplayName("CartBean#addItemメソッドのテストクラス")
	@TestInstance(Lifecycle.PER_CLASS)
	class AddItemTest {
		
		private List<ItemBean> itemsInCart = new ArrayList<>();
		
		@BeforeAll
		void init() {
			ItemBean bean = null;
			bean = new ItemBean(5, "The Racer", 1000);
			bean.setQuantity(2);
			itemsInCart.add(bean);
			bean = new ItemBean(8, "Invader Fighter", 3400);
			bean.setQuantity(3);
			itemsInCart.add(bean);
		}
		@AfterAll
		void finalise() {
			itemsInCart.clear();
			itemsInCart = null;
		}
		
		@Test
		@DisplayName("商品番号「５」の商品が２個と商品番号「８」の商品が３個入っている状態で商品番号「８」の商品を４個追加できる")
		@Disabled
		void test_13() {
			// setup
			ItemBean target = new ItemBean(8, "Invader Fighter", 3400);
			ItemBean bean = new ItemBean(8, "Invader Fighter", 3400);
			int quantity = 4;
			bean.setQuantity(7);
			List<ItemBean> expectedList = this.itemsInCart;
			expectedList.add(bean);
			// execute
			sut.addCart(new ItemBean(5, "The Racer", 1000), 2); // あらかじめ商品番号「５」の商品を２個追加
			sut.addCart(new ItemBean(8, "Invader Fighter", 3400), 3); // あらかじめ商品番号「８」の商品を３個追加
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドが実装されていません。");
			}
		}
		
		@Test
		@DisplayName("商品番号「５」の商品が２個入っている状態で商品番号「８」の商品を３個追加できる")
		void test_12() {
			// setup
			ItemBean target = new ItemBean(8, "Invader Fighter", 3400);
			int quantity = 3;
			List<ItemBean> expectedList = this.itemsInCart;
			// execute
			sut.addCart(new ItemBean(5, "The Racer", 1000), 2); // あらかじめ商品番号「５」の商品を２個追加
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドが実装されていません。");
			}
		}
		
		@Test
		@DisplayName("空のカートに商品番号「５」の商品を３個入れることができる")
		void test_11() {
			// setup
			ItemBean target = new ItemBean(5, "The Racer", 1000);
			ItemBean bean =  new ItemBean(5, "The Racer", 1000);
			int quantity = 3;
			bean.setQuantity(quantity);
			List<ItemBean> expectedList = new ArrayList<>();
			expectedList.add(bean);
			// execute
			sut.addCart(target, quantity);
			List<ItemBean> actualList = sut.getItems();
			// verify
			if (actualList.size() > 0) {
				for (int i = 0; i < actualList.size(); i++) {
					ItemBean actual = actualList.get(i);
					ItemBean expected = expectedList.get(i);
					assertThat(actual.toString(), is(expected.toString()));
				}
			} else {
				fail("テスト対象メソッドが実装されていません。");
			}
		}
	}
	
	@Nested
	@DisplayName("CartBean#constructorメソッドのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("インスタンス化できる")
		void test_01() {
			// execute
			Object actual = new CartBean();
			// verify
			assertThat(actual, is(instanceOf(CartBean.class)));
		}
	}
}

