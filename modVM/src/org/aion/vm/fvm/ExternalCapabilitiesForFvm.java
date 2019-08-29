package org.aion.vm.fvm;

import java.math.BigInteger;
import org.aion.crypto.HashUtil;
import org.aion.fastvm.IExternalCapabilities;
import org.aion.types.AionAddress;

/**
 * An implementation of the {@link IExternalCapabilities} interface for the Fvm.
 */
public final class ExternalCapabilitiesForFvm implements IExternalCapabilities {

    @Override
    public AionAddress computeNewContractAddress(AionAddress sender, BigInteger senderNonce) {
        return new AionAddress(HashUtil.calcNewAddr(sender.toByteArray(), senderNonce.toByteArray()));
    }

    @Override
    public byte[] hash256(byte[] payload) {
        return HashUtil.h256(payload);
    }
}