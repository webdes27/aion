package org.aion.zero.impl.valid;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.aion.mcf.blockchain.BlockHeader;
import org.slf4j.Logger;

/** validation rules depending on parent's block header */
public class ParentBlockHeaderValidator
        extends AbstractBlockHeaderValidator {


    private Map<Byte, List<DependentBlockHeaderRule>> chainRules;

    public ParentBlockHeaderValidator(Map<Byte, List<DependentBlockHeaderRule>> rules) {
        if (rules == null) {
            throw new NullPointerException();
        }
        chainRules = rules;
    }

    public boolean validate(BlockHeader header, BlockHeader parent, Logger logger) {
        return validate(header, parent, logger, null);
    }

    public boolean validate(BlockHeader header, BlockHeader parent, Logger logger, Object extraArg) {
        List<RuleError> errors = new LinkedList<>();
        List<DependentBlockHeaderRule> rules = chainRules.get(header.getSealType().getSealId());

        if (rules == null) {
            return false;
        } else {
            for (DependentBlockHeaderRule rule : rules) {
                if (!rule.validate(header, parent, errors, extraArg)) {
                    if (logger != null) logErrors(logger, errors);
                    return false;
                }
            }
        }

        return true;
    }
}