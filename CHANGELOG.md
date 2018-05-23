# Changelog 

## Version 0.1.9 - 23-05-2018

### Added

* This changelog
* HierarchyOperation cache the graph representation of the hierarchy dataset.
* InnerJoinOperation tries to forward the requested order to its children

### Changed

* Hierarchy operation does not call getData() on hierarchy dataset until its getData() method is called.
* InnerJoinOperation does not try to output Cartesian product of misses and instead simply clear its buffer.
* InnerJoinSpliterator now fully respect the Spliterator API.



