package la.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import la.bean.CategoryBean;
import la.bean.ItemBean;

class ItemDaoTest {
	
	/** テスト対象クラス：system under test */
	@SuppressWarnings("unused")
	private ItemDAO sut;

	@BeforeEach
	void setUp() throws Exception {
		this.sut = new ItemDAO();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Nested
	@DisplayName("ItemDAO#findByCategoryメソッドのテストクラス")
	class FindByCategoryTest {
		@Test
		@DisplayName("本を選択すると「Javaの基本」「MLB Fun」「料理BOOK!」を取得できる")
		void test_11() throws Exception {
			// setup
			int code = 1;
			List<ItemBean> expectedList = new ArrayList<>();
			ItemBean bean = null;
			bean= new ItemBean(1, "Javaの基本", 2500);
			expectedList.add(bean);
			bean= new ItemBean(2, "MLB Fun", 980);
			expectedList.add(bean);
			bean= new ItemBean(3, "料理BOOK!", 1200);
			expectedList.add(bean);
			
			// execute
			List<ItemBean> actualList = sut.findByCategory(code);
			
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				ItemBean actual = actualList.get(i);
				ItemBean expected = expectedList.get(i);
				assertThat(actual.toString(), is(expected.toString()));
			}
		}
	}
	
	@Nested
	@DisplayName("ItemDAO#findAllCategoryメソッドのテストクラス")
	class FindAllCategoryTest {
		@Test
		@DisplayName("すべての商品カテゴリーを取得できる")
		void test_01() throws Throwable {
			// setup
			List<CategoryBean> expectedList = new ArrayList<>();
			CategoryBean bean = null;
			bean = new CategoryBean(1, "本");
			expectedList.add(bean);
			bean = new CategoryBean(2, "DVD");
			expectedList.add(bean);
			bean = new CategoryBean(3, "ゲーム");
			expectedList.add(bean);
 			// execute
			List<CategoryBean> actualList = sut.findAllCategory();
			// verify
			for (int i = 0; i < actualList.size(); i++) {
				CategoryBean actual = actualList.get(i);
				CategoryBean expected = expectedList.get(i);
				assertThat(actual.toString(), is(expected.toString()));
			}
		}
	}

	@Nested
	@DisplayName("ItemDAO#constructorのテストクラス")
	class ConstructorTest {
		@Test
		@DisplayName("ItemDAO#connフィールドはデータベース接続オブジェクトのインスタンスである")
		void test_02() throws Exception {
			// setup
			ItemDAO target = new ItemDAO();
			Class<? extends ItemDAO> clazz = target.getClass();
			Field targetField = clazz.getDeclaredField("conn");
			targetField.setAccessible(true);
			// execute
			Object actual = targetField.get(target);
			// verify
			assertThat(actual, is(instanceOf(Connection.class)));
		}
		@Test
		@DisplayName("インスタンス化できる")
		void test_01() throws Exception {
			// execute
			Object actual = new ItemDAO();
			// verify
			assertThat(actual, is(instanceOf(ItemDAO.class)));
		}
	}

}
