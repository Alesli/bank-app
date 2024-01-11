import com.domain.jmp.bank.api.BankService;
import com.domain.jmp.service.api.SubscriptionService;

module jmp.application {
    uses BankService;
    uses SubscriptionService;
    requires bank.api;
    requires bank.impl;
    requires service.api;
    requires service.impl;
    requires dto;
}
