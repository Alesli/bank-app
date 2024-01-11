import com.domain.jmp.cloud.service.impl.CloudServiceImpl;
import com.domain.jmp.service.api.SubscriptionService;

module service.impl {
    requires transitive service.api;
    requires dto;
    provides SubscriptionService with CloudServiceImpl;
}
