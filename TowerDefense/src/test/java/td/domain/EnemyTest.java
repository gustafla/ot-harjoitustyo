package td.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EnemyTest {
	Enemy enemy;

	@Before
	public void setUp() {
		enemy = new Enemy(0, 0, 10, 10);
	}
}
