# Changelog 

## develop

### Added

* Support for average aggregation function `ds := avg(ds1) group by ds1.x`

### Changed



## 0.1.9 - 2018-05-23

### Added

* This changelog
* HierarchyOperation cache the graph representation of the hierarchy dataset.
* InnerJoinOperation tries to forward the requested order to its children

### Changed

* Add support non finite values in `round` and `floor` functions
* Hierarchy operation does not call `Dataset#getData()` on hierarchy dataset until `HierarchyOperation#getData()` method is called.
* `InnerJoinOperation` does not try to output Cartesian product of misses and instead simply clear its buffer.
* `InnerJoinSpliterator` now fully respect the `Spliterator` API.

##  0.1.8 - 2018-05-07

## 0.1.7 - 2018-04-25

## 0.1.6 - 2018-04-12



```bash
git log 0.1.8..HEAD --oneline --decorate --color --first-parent
```



