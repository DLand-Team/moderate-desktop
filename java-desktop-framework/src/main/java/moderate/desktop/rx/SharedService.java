package moderate.desktop.rx;

import io.reactivex.rxjava3.subjects.PublishSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedService {

	private static volatile SharedService instance;

	public Map<SharedType, PublishSubject<Object>> subjects = new HashMap<>(); // 实例

	/* 订阅 */
	public PublishSubject<Object> sub(SharedType topic) {
		return subjects.computeIfAbsent(topic, k -> PublishSubject.create());
	}

	/* 发布 */
	public void pub(SharedType topic, Object msg) {
		PublishSubject<Object> subject = subjects.get(topic);
		if (subject != null) {
			subject.onNext(msg);
		}
	}

	/* 销毁 */
	public void destroy(SharedType topic) {
		subjects.remove(topic);
	}

	/* 销毁 */
	public void destroys(List<SharedType> topics) {
		for (SharedType topic : topics) {
			destroy(topic);
		}
	}

	/* 清空 */
	public void destroyAll() {
		subjects.clear();
	}

	public static SharedService getInstance() {
		if (instance == null) {
			synchronized (SharedService.class) {
				if (instance == null) {
					instance = new SharedService();
				}
			}
		}
		return instance;
	}

}
