module jmp.application {
    uses com.domain.jmp.bank.api.Bank;
    uses com.domain.jmp.service.api.Service;
    requires bank.api;
    requires bank.impl;
    requires service.api;
    requires service.impl;
    requires dto;
}
