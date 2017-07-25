package net.zdsoft.common.template;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

@SuppressWarnings("serial")
public class ValueContextHashModel extends SimpleHash {

    private ValueContext context;

    public ValueContextHashModel(ValueContext context, ObjectWrapper wrapper) {
        super(wrapper);

        this.context = context;
    }

    @Override
    public TemplateModel get(String key) throws TemplateModelException {
        return wrap(context.get(key));
    }
}
