package com.learn.more.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.learn.more.entiry.ImmutableUser;
import com.learn.more.entiry.ImmutableUserMixin;

public class JacksonMixinModule extends SimpleModule {

  public JacksonMixinModule() {
    super(JacksonMixinModule.class.getName());
  }

  // 注册所有使用Mixin机制的类
  @Override
  public void setupModule(SetupContext context) {
    context.setMixInAnnotations(ImmutableUser.class, ImmutableUserMixin.class);
    // ......
  }
}
