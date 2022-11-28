package moderate.desktop;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import moderate.desktop.entity.ProcessInitializationResult;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ProgressBeanPostProcessor implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {

	private final AtomicInteger count = new AtomicInteger(0);

	private Integer total = 211;

	private final static Subject<ProcessInitializationResult> beans = BehaviorSubject.create();

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		count.incrementAndGet();
		ProcessInitializationResult result = new ProcessInitializationResult();
		result.setPerc(count.get() * 100 / total);
		result.setBeanName(bean.getClass().getPackageName() + "." + bean.getClass().getSimpleName());
		beans.onNext(result);
		// System.out.println(count.get());
		return bean;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
		beans.onComplete();
	}

	public static Observable<ProcessInitializationResult> observe() {
		return beans;
	}

}
