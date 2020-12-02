package com.movies.app.ui.common.listener;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class LoadMoreScrollListener_Factory implements Factory<LoadMoreScrollListener> {
  private static final LoadMoreScrollListener_Factory INSTANCE =
      new LoadMoreScrollListener_Factory();

  @Override
  public LoadMoreScrollListener get() {
    return new LoadMoreScrollListener();
  }

  public static Factory<LoadMoreScrollListener> create() {
    return INSTANCE;
  }
}
