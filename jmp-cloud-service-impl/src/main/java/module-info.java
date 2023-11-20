module service.impl {
    requires transitive service.api;
    requires dto;
    provides com.domain.jmp.service.api.Service with com.domain.jmp.cloud.service.impl.CloudServiceImpl;
}
