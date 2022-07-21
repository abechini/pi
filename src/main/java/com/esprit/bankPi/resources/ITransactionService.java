package com.esprit.bankPi.resources;

import java.io.ByteArrayInputStream;

public interface ITransactionService {
	ByteArrayInputStream exportExtrait(long idCompte);
}
